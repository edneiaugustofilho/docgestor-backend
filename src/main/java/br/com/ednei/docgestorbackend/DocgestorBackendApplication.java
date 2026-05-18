package br.com.ednei.docgestorbackend;

import br.com.ednei.docgestorbackend.util.ConsoleLogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DocgestorBackendApplication {

    @Value("${app.company.name}")
    private String companyName;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.version}")
    private String versao;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(DocgestorBackendApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printStartedBanner() {
        String labelName = ConsoleLogUtil.formatar("Nome", ConsoleLogUtil.Cor.AMARELO, ConsoleLogUtil.Estilo.NEGRITO);
        String formatarName = ConsoleLogUtil.formatar("%s", ConsoleLogUtil.Cor.AZUL);
        String labelVersao = ConsoleLogUtil.formatar("Versão", ConsoleLogUtil.Cor.AMARELO, ConsoleLogUtil.Estilo.NEGRITO);
        String formatarVersao = ConsoleLogUtil.formatar("%s", ConsoleLogUtil.Cor.ROXO);
        String labelProfile = ConsoleLogUtil.formatar("Profile", ConsoleLogUtil.Cor.AMARELO, ConsoleLogUtil.Estilo.NEGRITO);
        String formatarProfile = ConsoleLogUtil.formatar("%s", ConsoleLogUtil.Cor.CIANO);

        String labelPoweredBy = ConsoleLogUtil.formatar("Powered by", ConsoleLogUtil.Cor.AMARELO, ConsoleLogUtil.Estilo.NEGRITO);
        String formatarPoweredBy = ConsoleLogUtil.formatar("%s", ConsoleLogUtil.Cor.VERDE);

        String listeningPort = ConsoleLogUtil.formatar("Listening to port: ", ConsoleLogUtil.Cor.AMARELO, ConsoleLogUtil.Estilo.NEGRITO);
        String formatarListeningPort = ConsoleLogUtil.formatar("%s", ConsoleLogUtil.Cor.VERMELHO);

        String banner =
                ":: " + labelName + " " + formatarName + " ::\n" +
                        ":: " + labelVersao + " " + formatarVersao + " ::\n" +
                        ":: " + labelProfile + " " + formatarProfile + " ::\n" +
                        ":: " + labelPoweredBy + " " + formatarPoweredBy + " ::\n"+
                        ":: " + listeningPort + " " + formatarListeningPort + " ::\n"
                ;

        System.out.printf((banner) + "%n", applicationName, versao, activeProfile, companyName, port);
    }

}
