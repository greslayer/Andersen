package tasks.jdbc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Controller
public class ConsoleReader implements InitializingBean {
    @Autowired
    private InputManager manager;

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Input Command create,read,update,delete or exit");
            try {
                String input = reader.readLine();
                if (input.equals("exit")) {
                    break;
                }
                System.out.println("Input arguments");
                String argString = reader.readLine();
                String[] args = argString.split(" ");
                manager.manage(input, args);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }
}
