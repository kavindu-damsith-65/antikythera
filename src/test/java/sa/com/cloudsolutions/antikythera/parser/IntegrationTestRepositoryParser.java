package sa.com.cloudsolutions.antikythera.parser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sa.com.cloudsolutions.antikythera.configuration.Settings;
import sa.com.cloudsolutions.antikythera.evaluator.AntikytheraRunTime;
import sa.com.cloudsolutions.antikythera.generator.RepositoryQuery;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IntegrationTestRepositoryParser {
    @BeforeAll
    public static void setup() throws IOException {
        Settings.loadConfigMap(new File("src/test/resources/generator.yml"));
    }

    @Test
    void testDepartmentRepositoryParser() throws IOException {
        final RepositoryParser tp = new RepositoryParser();
        tp.preProcess();
        tp.compile(AbstractCompiler.classToPath("sa.com.cloudsolutions.repository.DepartmentRepository"));
        tp.process();

        final CompilationUnit cu = AntikytheraRunTime.getCompilationUnit("sa.com.cloudsolutions.service.Service");
        assertNotNull(cu);

        MethodDeclaration md = cu.findFirst(MethodDeclaration.class,
                md1 -> md1.getNameAsString().equals("queries2")).get();

        md.accept(new VoidVisitorAdapter<Void>() {
            public void visit(MethodCallExpr n, Void arg) {
                super.visit(n, arg);
                MethodDeclaration md = tp.findMethodDeclaration(n);
                assertNotNull(md);
                RepositoryQuery rql = tp.get(md);
                System.out.println(n);
            }
        }, null);
    }

    @Test
    void testPersonRepositoryParser() throws IOException {
        final RepositoryParser tp = new RepositoryParser();
        tp.preProcess();
        tp.compile(AbstractCompiler.classToPath("sa.com.cloudsolutions.repository.PersonRepository"));
        tp.process();

        final CompilationUnit cu = AntikytheraRunTime.getCompilationUnit("sa.com.cloudsolutions.service.Service");
        assertNotNull(cu);

        MethodDeclaration md = cu.findFirst(MethodDeclaration.class).get();
        md.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodCallExpr n, Void arg) {
                super.visit(n, arg);
                MethodDeclaration md = tp.findMethodDeclaration(n);
                if(md == null) {
                    return;
                }

                RepositoryQuery rql = tp.get(md);
                assertNotNull(rql);

                if(n.getNameAsString().equals("findById")) {
                    assertEquals("SELECT * FROM person WHERE id = ? ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByAgeBetween")) {
                    assertEquals("SELECT * FROM person WHERE age BETWEEN ? AND ? ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByAge")) {
                    assertEquals("SELECT * FROM person WHERE age = ? ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByAgeGreaterThan")) {
                    assertEquals("SELECT * FROM person WHERE age > ? ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByAgeLessThan")) {
                    assertEquals("SELECT * FROM person WHERE age < ? ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByAgeLessThanEqual")) {
                    assertEquals("SELECT * FROM person WHERE age <= ? ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByAgeGreaterThanEqual")) {
                    assertEquals("SELECT * FROM person WHERE age >= ? ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByAgeIn")) {
                    assertEquals("SELECT * FROM person WHERE age In  (?) ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByAgeNotIn")) {
                    assertEquals("SELECT * FROM person WHERE age NOT In (?) ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByAgeIsNull")) {
                    assertEquals("SELECT * FROM person WHERE age IS NULL ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByAgeIsNotNull")) {
                    assertEquals("SELECT * FROM person WHERE age IS NOT NULL ", rql.getQuery());
                }
                else if(n.getNameAsString().equals("findByNameLike")) {
                    assertEquals("SELECT * FROM person WHERE name LIKE ? ", rql.getQuery());
                }
            }
        }, null);
    }
}