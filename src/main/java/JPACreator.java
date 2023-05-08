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

    public JPACreator() {
        schemaMap = loadDBStructureFromSpecificDB();
        fillEntityFile();
        fillDtoFiles();
        fillDaoFile();
//        fillDaoImplFiles();
    }

    // =================================================================== HIBERNATE ===================================
    private Map<String, ValidatedColumns> loadDBStructureFromSpecificDB() {
        Map<String, ValidatedColumns> resultMap = new HashMap<>();

        SessionFactory factory = new Configuration().configure(configFile).buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("" + "SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE, COLUMN_KEY " + "FROM INFORMATION_SCHEMA.COLUMNS " + "WHERE TABLE_SCHEMA=:db");
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
                resultMap.get(tableName).getColumns().add(0, new Column(object[1].toString(), object[2].toString()));
            } else {
                resultMap.get(tableName).getColumns().add(new Column(object[1].toString(), object[2].toString()));
            }
        });
        return resultMap;
    }
    // =================================================================== HIBERNATE ===================================

    // =================================================================== ENTITY FILES ===============================
    private void fillEntityFile() {
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
                writer.write("import javax.persistence.Table;\n\n");
                writer.write("@Data\n");
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
    private void fillDtoFiles() {
        System.out.println("GENERATE DTOS");
        createFiles(schemaMap, JPAType.DTO).forEach((tableName, fileObject) -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileObject.getFile()));
                writer.write("package dto;\n\n");
                writer.write("import lombok.AllArgsConstructor;\n");
                writer.write("import lombok.Data;\n");
                writer.write("import lombok.NoArgsConstructor;\n\n");
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
    private void fillDaoFile() {
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

    private void fillDaoImplFiles() {
        System.out.println("GENERATE DAO IMPLEMENTS");
        final List<String> tableTitles = new ArrayList<>();
        createFiles(schemaMap, JPAType.DAO_IMPL).forEach((tableName, fileObject) -> {
            String title = fileObject.getTableTitle();
            tableTitles.add(title);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileObject.getFile()));
                writer.write("package safe.jpa.daoimpl;\n\n");
                writer.write(String.format("import safe.dto.%s;\n", title));
                writer.write(String.format("import safe.jpa.dao.%s%s;\n\n", title, JPAType.DAO.suffix));
                writer.write("import java.util.List;\n\n");
                writer.write(String.format("public class %s%s extends EntityManager implements %s%s<%s> {\n\n", title, JPAType.DAO_IMPL.suffix, title, JPAType.DAO.suffix, title));
                writer.write("    @Override\n");
                writer.write(String.format("    public void saveOrUpdate(%s %s) {\n\n", title, title.split("")[0].toLowerCase() + title.substring(1)));
                writer.write("    }\n\n");
                writer.write("    @Override\n");
                writer.write(String.format("    public %s get%s(int id) {\n", title, title));
                writer.write("        return null;\n");
                writer.write("    }\n\n");
                writer.write("    @Override\n");
                writer.write(String.format("    public List<%s> getAll%s() {\n", title, title));
                writer.write("        return null;\n");
                writer.write("    }\n\n");
                writer.write("    @Override\n");
                writer.write(String.format("    public void delete%s(%s %s) {\n\n", title, title, title.split("")[0].toLowerCase() + title.substring(1)));
                writer.write("    }\n\n");
                writer.write("    @Override\n");
                writer.write(String.format("    public void delete%s(int id) {\n", title));
                writer.write("    }\n\n");
                writer.write("}");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        generateManagerEntity(tableTitles);
        System.out.println("DAO IMPLEMENTS done...");
    }

    // =================================================================== DAO IMPLEMENTS FILES ========================


    // =================================================================== ENTITY MANAGER ==============================

    private void generateManagerEntity(List<String> titles) {
        System.out.println("GENERATE ENTITY MANAGER");
        Map<String, ValidatedColumns> entityManagerFile = new HashMap<>();
        entityManagerFile.put("EntityManager", new ValidatedColumns());
        createFiles(entityManagerFile, JPAType.ENTITY_MANAGER).forEach((classFile, fileObject) -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileObject.getFile()));
                writer.write("package safe.jpa.daoimpl;\n\n");
                writer.write("import safe.jpa.entity.*;\n");
                writer.write("import lombok.Data;\n");
                writer.write("import org.hibernate.Session;\n");
                writer.write("import org.hibernate.SessionFactory;\n");
                writer.write("import org.hibernate.cfg.Configuration;\n\n");
                writer.write("@Data\n");
                writer.write(String.format("class %s {\n\n", classFile));
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
                writer.write("        if (session == null) {\n");
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

    public Map<String, FileObject> createFiles(Map<String, ValidatedColumns> schemaMap, JPAType jpaType) {
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

    public static void main(String[] args) {
        new JPACreator();
    }

    private String getDataType(String sqlDataType) {
        switch (sqlDataType) {
            case "varchar":
                return "String";
            case "char":
                return "Character";
            case "tiny":
            case "int":
                return "Integer";
            case "float":
                return "Float";
            case "bool":
                return "Boolean";
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
            resultName += word.split("")[0].toUpperCase() + word.substring(1);
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
        ENTITY("Entity", "src/main/java/jpa/entity/"), DAO("Dao", "src/main/java/jpa/dao/"), DAO_IMPL("DaoImpl", "src/main/java/jpa/daoimpl/"), DTO("Dto", "src/main/java/dto/"), ENTITY_MANAGER("", "src/main/java/jpa/daoimpl/");

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
