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
    private String entityPath = "src/main/java/jpa/entity/";
    private String daoPath = "src/main/java/jpa/dao/";
    private String daoImplPath = "src/main/java/jpa/daoimpl/";

    private Map<String, ValidatedColumns> schemaMap;

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
        ENTITY("Entity"),
        DAO("Dao"),
        DAO_IMPL("DaoImpl)");

        private String suffix;

        JPAType(String suffix) {
            this.suffix = suffix;
        }
    }
    // =================================================================== HIBERNATE ===================================


    // =================================================================== ENTITY FILES ===============================

    private void fillEntityFile() {
        createFiles(schemaMap, JPAType.ENTITY).forEach((tableName, file) -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                System.out.println("GENERATE PACKAGE");
                writer.write("package jpa.entity;\n\n");
                System.out.println("GENERATE IMPORTS");
                writer.write("import lombok.Data;\n");
                writer.write("import lombok.NoArgsConstructor;\n\n");
                writer.write("import javax.persistence.CascadeType;\n");
                writer.write("import javax.persistence.Column;\n");
                writer.write("import javax.persistence.Entity;\n");
                writer.write("import javax.persistence.FetchType;\n");
                writer.write("import javax.persistence.GeneratedValue;\n");
                writer.write("import javax.persistence.GenerationType;\n");
                writer.write("import javax.persistence.Id;\n");
                writer.write("import javax.persistence.JoinColumn;\n");
                writer.write("import javax.persistence.ManyToOne;\n");
                writer.write("import javax.persistence.OneToMany;\n");
                writer.write("import javax.persistence.Table;\n");
                writer.write("import java.util.ArrayList;\n");
                writer.write("import java.util.List;\n\n");
                System.out.println("GENERATE CLASS HEADER");
                writer.write("@Data\n");
                writer.write("@NoArgsConstructor\n");
                writer.write("@Entity\n");
                writer.write(String.format("@Table(name = \"%s\")\n", tableName));
                writer.write(String.format("public class %s {\n\n", file.getName().split("\\.")[0]));
                System.out.println("GENERATE PARAMETERS");
                writer.write("    @Id\n");
                writer.write("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");

                schemaMap.get(tableName).getColumns().stream().forEach(column -> {
                    try {
                        writer.write(String.format("    @Column(name = \"%s\")\n", column.getColumnName()));
                        writer.write(String.format("    private %s %s;\n\n", getDataType(column.getDataType()), formatName(column.getColumnName(), JPAType.ENTITY)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                System.out.println("FINISHING FILE");
                writer.write("}");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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

    private String formatName(String key, JPAType jpaType) {
        return this.formatName(key, false, jpaType);
    }

    private String formatName(String key, boolean isClass, JPAType jpaType) {
        String resultName = "";
        key.replace("-", "_");
        String[] words = key.split("_");
        for (String word : words) {
            resultName += word.split("")[0].toUpperCase() + word.substring(1);
        }
        if (!isClass) {
            resultName = resultName.split("")[0].toLowerCase() + resultName.substring(1);
        } else {
            resultName += jpaType.getSuffix();
        }
        return resultName;
    }
    // =================================================================== ENTITY FILES ===============================


    // =================================================================== DAO FILES ===============================


    // =================================================================== DAO FILES ===============================
    public Map<String, File> createFiles(Map<String, ValidatedColumns> schemaMap, JPAType jpaType) {
        if (schemaMap == null || schemaMap.keySet().isEmpty()) {
            return null;
        }
        if (!entityPath.endsWith("/")) {
            entityPath += "/";
        }
        if (!new File(entityPath).exists()) {
            new File(entityPath).mkdirs();
        }
        final Map<String, File> files = new HashMap<>();
        schemaMap.keySet().stream().forEach(tableName -> {
            File file = new File(String.format("%s%s%s", entityPath, formatName(tableName, true, jpaType), ".java"));
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            files.put(tableName, file);
        });
        return files;
    }

    public JPACreator() {
        schemaMap = loadDBStructureFromSpecificDB();
        fillEntityFile();
    }

    public static void main(String[] args) {
        new JPACreator();
    }
}
