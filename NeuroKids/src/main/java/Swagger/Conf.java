package Swagger;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do Swagger/OpenAPI 3.
 * Acesse: <a href="http://localhost:8080/swagger-ui.html">...</a>
 */
@Configuration
public class Conf {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Neurodivergente Platform API")
                        .version("1.0.0")
                        .description("""
                                API REST para a plataforma de suporte a crianças neurodivergentes.
                                
                                Funcionalidades:
                                - Gestão de Pacientes e Responsáveis
                                - Atividades educativas e lúdicas
                                - Acompanhamento terapêutico
                                - Comunicação família-escola
                                - Progresso e relatórios
                                """)
                        .contact(new Contact()
                                .name("Equipe Neurodivergente")
                                .email("contato@neurodivergente.com.br"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}