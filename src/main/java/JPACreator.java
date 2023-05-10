import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPACreator {

    private final String configFile = "hibernate.cfg.xml";
    private final String db = "bf2stats";

    private Map<String, ValidatedColumns> schemaMap;
    private final List<String> tableTitles = new ArrayList<>();

    public JPACreator() {
//        schemaMap = loadDBStructureFromSpecificDB();
//        generateEntityFile();
//        generateDtoFiles();
//        generateDaoFile();
//        generateDaoImplFiles();
//        generateManagerEntity(tableTitles);
    }

    // =================================================================== HIBERNATE ===================================
    private Map<String, ValidatedColumns> loadDBStructureFromSpecificDB() {
        Map<String, ValidatedColumns> resultMap = new HashMap<>();

        SessionFactory factory = new Configuration().configure(configFile).buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("SELECT TABLE_NAME, COLUMN_NAME, COLUMN_TYPE, COLUMN_KEY FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=:db");
        query.setParameter("db", db);
        List<Object[]> list = query.getResultList();
        session.getTransaction().commit();
        session.close();
        factory.close();

        list.forEach(object -> {
            String tableName = object[0].toString();
            if (!resultMap.containsKey(tableName)) {
                resultMap.put(tableName, new ValidatedColumns());
            }
            if (!resultMap.get(tableName).isPrimaryKeyIncluded() && "PRI".equals(object[3].toString())) {
                resultMap.get(tableName).setPrimaryKeyIncluded(true);
                resultMap.get(tableName).getColumns().add(0, new Column(object[1].toString(), object[2].toString().split(" ")[0]));
            } else {
                resultMap.get(tableName).getColumns().add(new Column(object[1].toString(), object[2].toString().split(" ")[0]));
            }
        });
        return resultMap;
    }
    // =================================================================== HIBERNATE ===================================

    // =================================================================== ENTITY FILES ===============================
    private void generateEntityFile() {
        System.out.println("GENERATE ENTITIES");
        createFiles(schemaMap, JPAType.ENTITY).forEach((tableName, fileObject) -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileObject.getFile()));
                writer.write("package jpa.entity;\n\n");
                writer.write("import lombok.Data;\n");
                writer.write("import lombok.NoArgsConstructor;\n\n");
                writer.write("import javax.persistence.Column;\n");
                writer.write("import javax.persistence.Entity;\n");
                writer.write("import javax.persistence.GeneratedValue;\n");
                writer.write("import javax.persistence.GenerationType;\n");
                writer.write("import javax.persistence.Id;\n");
                writer.write("import javax.persistence.Table;\n");
                for (Column column : schemaMap.get(tableName).getColumns()) {
                    if ("datetime".equals(column.getDataType())) {
                        writer.write("import java.time.LocalDateTime;\n");
                        break;
                    }
                }
                writer.write("\n@Data\n");
                writer.write("@NoArgsConstructor\n");
                writer.write("@Entity\n");
                writer.write(String.format("@Table(name = \"%s\")\n", tableName));
                writer.write(String.format("public class %s {\n\n", fileObject.getFile().getName().split("\\.")[0]));
                writer.write("    @Id\n");
                writer.write("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");

                schemaMap.get(tableName).getColumns().stream().forEach(column -> {
                    try {
                        writer.write(String.format("    @Column(name = \"%s\")\n", column.getColumnName()));
                        writer.write(String.format("    private %s %s%s;\n\n", getDataType(column.getDataType()), formatName(column.getColumnName()), JPAType.ENTITY.getSuffix()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.write("}");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("ENTITIES done ...");
    }
    // =================================================================== ENTITY FILES ================================

    // =================================================================== DTO FILES ===================================
    private void generateDtoFiles() {
        System.out.println("GENERATE DTOS");
        createFiles(schemaMap, JPAType.DTO).forEach((tableName, fileObject) -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileObject.getFile()));
                writer.write("package business.dto;\n\n");
                writer.write("import lombok.AllArgsConstructor;\n");
                writer.write("import lombok.Data;\n");
                writer.write("import lombok.NoArgsConstructor;\n\n");
                for (Column column : schemaMap.get(tableName).getColumns()) {
                    if ("datetime".equals(column.getDataType())) {
                        writer.write("import java.time.LocalDateTime;\n\n");
                        break;
                    }
                }
                writer.write("@Data\n");
                writer.write("@AllArgsConstructor\n");
                writer.write("@NoArgsConstructor\n");
                writer.write(String.format("public class %s {\n\n", fileObject.getFile().getName().split("\\.")[0]));
                schemaMap.get(tableName).getColumns().stream().forEach(column -> {
                    try {
                        writer.write(String.format("    private %s %s;\n\n", getDataType(column.getDataType()), formatName(column.getColumnName())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.write("}");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("DTOS done ...");
    }
    // =================================================================== DTO FILES ===================================

    // =================================================================== DAO FILES ===================================
    private void generateDaoFile() {
        System.out.println("GENERATE DAOS");
        createFiles(schemaMap, JPAType.DAO).forEach((tableName, fileObject) -> {
            String dtoClass = fileObject.getTableTitle();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileObject.getFile()));
                writer.write("package jpa.dao;\n\n");
                writer.write("import java.util.List;\n\n");
                writer.write(String.format("public interface %s<%s> {\n\n", fileObject.getFile().getName().split("\\.")[0], dtoClass));
                writer.write(String.format("    void saveOrUpdate(%s %s);\n\n", dtoClass, dtoClass.split("")[0].toLowerCase() + dtoClass.substring(1)));
                writer.write(String.format("    %s get%s(int id);\n\n", dtoClass, dtoClass));
                writer.write(String.format("    List<%s> getAll%s();\n\n", dtoClass, dtoClass));
                writer.write(String.format("    void delete%s(%s %s);\n\n", dtoClass, dtoClass, dtoClass.split("")[0].toLowerCase() + dtoClass.substring(1)));
                writer.write(String.format("    void delete%s(int id);\n\n", dtoClass));
                writer.write("}");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("DAOS done ...");
    }
    // =================================================================== DAO FILES ===================================

    // =================================================================== DAO IMPLEMENTS FILES ========================
    private void generateDaoImplFiles() {
        System.out.println("GENERATE DAO IMPLEMENTS");
        createFiles(schemaMap, JPAType.DAO_IMPL).forEach((tableName, fileObject) -> {
            String title = fileObject.getTableTitle();
            tableTitles.add(title);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileObject.getFile()));
                writer.write("package jpa.daoimpl;\n\n");
                writer.write(String.format("import business.dto.%s;\n", title));
                writer.write("import jpa.EntityManager;\n");
                writer.write(String.format("import jpa.dao.%s%s;\n", title, JPAType.DAO.getSuffix()));
                writer.write(String.format("import jpa.entity.%s%s;\n", title, JPAType.ENTITY.getSuffix()));
                writer.write("import org.hibernate.query.Query;\n\n");
                writer.write("import java.util.ArrayList;\n");
                writer.write("import java.util.List;\n\n");
                writer.write(String.format("public class %s%s extends EntityManager implements %s%s<%s> {\n\n", title, JPAType.DAO_IMPL.suffix, title, JPAType.DAO.suffix, title));
                writer.write("    @Override\n");
                writer.write(String.format("    public void saveOrUpdate(%s %s) {\n", title, title.split("")[0].toLowerCase() + title.substring(1)));
                writer.write(String.format("        if (%s != null) {\n", title.split("")[0].toLowerCase() + title.substring(1)));
                writer.write("            getSession().beginTransaction();\n");
                writer.write(String.format("            getSession().saveOrUpdate(mapDtoToEntity(%s));\n", title.split("")[0].toLowerCase() + title.substring(1)));
                writer.write("            getSession().getTransaction().commit();\n");
                writer.write("            getSession().close();\n");
                writer.write("        }\n");
                writer.write("    }\n\n");
                writer.write("    @Override\n");
                writer.write(String.format("    public %s get%s(int id) {\n", title, title));
                writer.write("        getSession().beginTransaction();\n");
                writer.write(String.format("        %s%s entity = getSession().get(%s%s.class, id);\n", title, JPAType.ENTITY.getSuffix(), title, JPAType.ENTITY.getSuffix()));
                writer.write("        getSession().getTransaction().commit();\n");
                writer.write("        getSession().close();\n");
                writer.write(String.format("        return mapEntityToDto(entity);\n"));
                writer.write("    }\n\n");
                writer.write("    @Override\n");
                writer.write(String.format("    public List<%s> getAll%s() {\n", title, title));
                writer.write("        getSession().beginTransaction();\n");
                writer.write(String.format("        List<%s%s> entities = getSession().createQuery(\"FROM %s%s\").getResultList();\n", title, JPAType.ENTITY.getSuffix(), title, JPAType.ENTITY.getSuffix()));
                writer.write("        getSession().getTransaction().commit();\n");
                writer.write("        getSession().close();\n");
                writer.write(String.format("        List<%s> dtos = new ArrayList<>();\n", title));
                writer.write("        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));\n");
                writer.write("        return dtos;\n");
                writer.write("    }\n\n");
                writer.write("    @Override\n");
                writer.write(String.format("    public void delete%s(%s %s) {\n", title, title, title.split("")[0].toLowerCase() + title.substring(1)));
                writer.write(String.format("        if (%s != null) {\n", title.split("")[0].toLowerCase() + title.substring(1)));
                writer.write("            getSession().beginTransaction();\n");
                writer.write(String.format("            getSession().delete(mapDtoToEntity(%s));\n", title.split("")[0].toLowerCase() + title.substring(1)));
                writer.write("            getSession().getTransaction().commit();\n");
                writer.write("            getSession().close();\n");
                writer.write("        }\n");
                writer.write("    }\n\n");
                writer.write("    @Override\n");
                writer.write(String.format("    public void delete%s(int id) {\n", title));
                writer.write("        getSession().beginTransaction();\n");
                writer.write(String.format("        Query query = getSession().createQuery(\"DELETE FROM %s%s WHERE id=:id\");\n", title, JPAType.ENTITY.getSuffix()));
                writer.write("        query.setParameter(\"id\", id);\n");
                writer.write("        query.executeUpdate();\n");
                writer.write("        getSession().getTransaction().commit();\n");
                writer.write("        getSession().close();\n");
                writer.write("    }\n\n");
                writer.write(String.format("    private %s mapEntityToDto(%s%s entity) {\n", title, title, JPAType.ENTITY.getSuffix()));
                writer.write("        if (entity == null) {\n");
                writer.write("            return null;\n");
                writer.write("        }\n");
                writer.write(String.format("        %s dto = new %s();\n", title, title));
                schemaMap.get(tableName).getColumns().forEach(column -> {
                    try {
                        writer.write(String.format("        dto.set%s(entity.get%s%s());\n", formatName(column.getColumnName(), true), formatName(column.getColumnName(), true), JPAType.ENTITY.getSuffix()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.write("        return dto;\n");
                writer.write("    }\n\n");
                writer.write(String.format("    private %s%s mapDtoToEntity(%s dto) {\n", title, JPAType.ENTITY.getSuffix(), title));
                writer.write(String.format("        %s%s entity = new %s%s();\n", title, JPAType.ENTITY.getSuffix(), title, JPAType.ENTITY.getSuffix()));
                schemaMap.get(tableName).getColumns().forEach(column -> {
                    try {
                        writer.write(String.format("        entity.set%s%s(dto.get%s());\n", formatName(column.getColumnName(), true), JPAType.ENTITY.getSuffix(), formatName(column.getColumnName(), true)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.write("        return entity;\n");
                writer.write("    }\n\n");
                writer.write("}");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("DAO IMPLEMENTS done...");
    }
    // =================================================================== DAO IMPLEMENTS FILES ========================

    // =================================================================== ENTITY MANAGER ==============================

    /**
     * IMPORTANT!
     * Titles parameter is getting from DAO_IMPL generating, so its needed to call this method AFTER
     * generateDaoImplFiles() method!!
     *
     * @param titles (filled from generateDaoImplFiles() method!)
     */
    private void generateManagerEntity(List<String> titles) {
        System.out.println("GENERATE ENTITY MANAGER");
        Map<String, ValidatedColumns> entityManagerFile = new HashMap<>();
        entityManagerFile.put("EntityManager", new ValidatedColumns());
        createFiles(entityManagerFile, JPAType.ENTITY_MANAGER).forEach((classFile, fileObject) -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileObject.getFile()));
                writer.write("package jpa;\n\n");
                writer.write("import jpa.entity.*;\n");
                writer.write("import lombok.Data;\n");
                writer.write("import org.hibernate.Session;\n");
                writer.write("import org.hibernate.SessionFactory;\n");
                writer.write("import org.hibernate.cfg.Configuration;\n\n");
                writer.write("@Data\n");
                writer.write(String.format("public class %s {\n\n", classFile));
                writer.write("    private SessionFactory factory;\n");
                writer.write("    private Session session;\n\n");
                writer.write(String.format("    protected %s() {\n", classFile));
                writer.write("        factory = new Configuration()\n");
                writer.write(String.format("                .configure(\"%s\")\n", configFile));
                titles.forEach(title -> {
                    try {
                        writer.write(String.format("                .addAnnotatedClass(%s%s.class)\n", title, JPAType.ENTITY.getSuffix()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.write("                .buildSessionFactory();\n");
                writer.write("    }\n\n");
                writer.write("    public Session getSession() {\n");
                writer.write("        if (session == null || !session.isOpen()) {\n");
                writer.write("            session = getFactory().getCurrentSession();\n");
                writer.write("        }\n");
                writer.write("        return session;\n");
                writer.write("    }\n");
                writer.write("}");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("ENTITY MANAGER done...");
    }
    // =================================================================== ENTITY MANAGER ==============================

    public static void main(String[] args) {
        new JPACreator();
    }

    private Map<String, FileObject> createFiles(Map<String, ValidatedColumns> schemaMap, JPAType jpaType) {
        if (schemaMap == null || schemaMap.keySet().isEmpty()) {
            return null;
        }
        final String path = jpaType.getPath();
        if (!new File(path).exists()) {
            new File(path).mkdirs();
        }
        final Map<String, FileObject> resultMap = new HashMap<>();
        schemaMap.keySet().stream().forEach(tableName -> {
            File file = new File(String.format("%s%s%s%s", path, formatName(tableName, true), jpaType != JPAType.DTO ? jpaType.getSuffix() : "", ".java"));
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resultMap.put(tableName, new FileObject(formatName(tableName, true), file));
        });
        return resultMap;
    }

    private String getDataType(String sqlDataType) {
        if (sqlDataType.contains("varchar") || sqlDataType.contains("char")) {
            return "String";
        }
        switch (sqlDataType) {
            case "bigint(20)":
            case "int(10)":
            case "int(11)":
                return "Long";
            case "mediumint(7)":
            case "int(6)":
            case "int(8)":
                return "Integer";
            case "tinyint(1)":
            case "tinyint(2)":
            case "smallint(3)":
                return "Short";
            case "datetime":
                return "LocalDateTime";
            default:
                try {
                    throw new Exception("Unknown format");
                } catch (Exception e) {
                }
                return null;
        }
    }

    private String formatName(String key) {
        return this.formatName(key, false);
    }

    private String formatName(String key, boolean isClass) {
        String resultName = "";
        key.replace("-", "_");
        String[] words = key.split("_");
        for (String word : words) {
            if (word.length() > 0) {
                resultName += word.split("")[0].toUpperCase() + word.substring(1);
            }
        }
        if (!isClass) {
            resultName = resultName.split("")[0].toLowerCase() + resultName.substring(1);
        }
        return resultName;
    }

    @Data
    private class ValidatedColumns {
        private List<Column> columns = new ArrayList<>();
        private boolean primaryKeyIncluded = false;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Column {

        private String columnName;
        private String dataType;

        @Override
        public String toString() {
            return columnName;
        }
    }

    @Getter
    private enum JPAType {
        ENTITY("Entity", "src/main/java/jpa/entity/"), DAO("Dao", "src/main/java/jpa/dao/"), DAO_IMPL("DaoImpl", "src/main/java/jpa/daoimpl/"), DTO("Dto", "src/main/java/business/dto/"), ENTITY_MANAGER("", "src/main/java/jpa/");

        private String suffix;
        private String path;

        JPAType(String suffix, String path) {
            this.suffix = suffix;
            this.path = path;
            if (!path.endsWith("/")) {
                this.path += "/";
            }
        }
    }

    @Data
    @AllArgsConstructor
    private class FileObject {
        private String tableTitle;
        private File file;
    }
}
