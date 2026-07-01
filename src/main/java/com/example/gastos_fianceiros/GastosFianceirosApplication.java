package com.example.gastos_fianceiros;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.MapPropertySource;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GastosFianceirosApplication {

    public static void main(String[] args) {
        // 1. Carrega o arquivo .env
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // 2. Cria um contexto de aplicação inicial
        SpringApplication app = new SpringApplication(GastosFianceirosApplication.class);

        // 3. Adiciona as variáveis do .env ao Environment do Spring
        app.addInitializers(context -> {
            Map<String, Object> envVars = new HashMap<>();
            dotenv.entries().forEach(entry -> envVars.put(entry.getKey(), entry.getValue()));
            context.getEnvironment().getPropertySources().addFirst(new MapPropertySource("dotenv", envVars));
        });

        // 4. Inicia a aplicação
        app.run(args);
    }

	

}