package cn.yam.backmanage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URI;

@SpringBootTest
class BackmanageApplicationTests {

    @Test
    void contextLoads() {

                try {

                    //两个绝对路径
                    File absolutePath1 = new File("D:\\B_IDEA\\testE_09\\backmanage\\file\\useravatar");
                    System.out.println("绝对路径1: " + absolutePath1);
                    File absolutePath2 = new File("D:\\B_IDEA\\testE_09\\backmanage\\src\\main\\java\\cn\\yam\\backmanage\\controller\\ImgsHandleControler.java");
                    System.out.println("绝对路径2: " + absolutePath2);

                    //将绝对路径转换为URI
                    URI path1 = absolutePath1.toURI();
                    URI path2 = absolutePath2.toURI();

                    //从两个路径创建相对路径
                    URI relativePath = path2.relativize(path1);

                    //将URI转换为字符串
                    String path = relativePath.getPath();

                    System.out.println("相对路径: " + path);


                } catch (Exception e) {
                    e.getStackTrace();
                }
            }



}
