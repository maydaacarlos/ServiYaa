package com.upeu.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayInfoController {

    @Value("${server.port}")
    private String port;

    @Value("${spring.profiles.active:default}")
    private String profile;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        boolean prod = "prod".equalsIgnoreCase(profile);
        String label = prod ? "PRODUCCION" : "DESARROLLO";
        String color = prod ? "#0f7b3f" : "#5b2aa8";
        String background = prod ? "#eefaf2" : "#f4f0ff";
        return """
                <!doctype html>
                <html lang="es">
                <head>
                  <meta charset="utf-8">
                  <title>ServiYa Gateway %s</title>
                  <style>
                    body { margin: 0; font-family: Arial, sans-serif; background: %s; color: #17202a; }
                    main { max-width: 760px; margin: 48px auto; padding: 32px; background: white; border: 1px solid #d8dee8; border-radius: 8px; }
                    .badge { display: inline-block; padding: 8px 12px; border-radius: 6px; color: white; background: %s; font-weight: 700; }
                    h1 { margin: 18px 0 8px; }
                    .meta { color: #52606d; margin-bottom: 24px; }
                    .links { display: grid; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); gap: 10px; }
                    a { display: block; padding: 12px 14px; border: 1px solid #d8dee8; border-radius: 6px; color: %s; text-decoration: none; font-weight: 600; }
                    a:hover { background: %s; }
                  </style>
                </head>
                <body>
                  <main>
                    <span class="badge">%s</span>
                    <h1>ServiYa API Gateway</h1>
                    <p class="meta">Perfil: %s | Puerto: %s</p>
                    <section class="links">
                      <a href="/swagger-ui.html">Swagger UI</a>
                      <a href="/actuator/health">Health</a>
                      <a href="/api/v1/tecnicos/1">MS3 Technician</a>
                      <a href="/api/v1/solicitudes/2">MS4 Service Request</a>
                      <a href="/api/v1/asignaciones/instancia">MS5 Assignment</a>
                      <a href="/api/v1/pagos/1">MS6 Payment</a>
                    </section>
                  </main>
                </body>
                </html>
                """.formatted(label, background, color, color, background, label, profile, port);
    }
}
