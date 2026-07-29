import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class HelloWorld {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(
                //new InetSocketAddress("localhost", 50050), 0
                new InetSocketAddress("0.0.0.0", 50050), 0
        );

        server.createContext("/", exchange -> {

            String javaVersion = System.getProperty("java.version");
            String serverTime = LocalDateTime.now().toString();

            String response = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta charset="UTF-8">
                        <title>CodeNest</title>

                        <style>
                            body {
                                margin: 0;
                                font-family: Arial, sans-serif;
                                background: #f4f7fb;
                                color: #333;
                            }

                            header {
                                background: #2563eb;
                                color: white;
                                padding: 40px;
                                text-align: center;
                            }

                            header h1 {
                                margin: 0;
                                font-size: 45px;
                            }

                            header p {
                                font-size: 18px;
                            }

                            .container {
                                max-width: 900px;
                                margin: 40px auto;
                                padding: 20px;
                            }

                            .welcome {
                                background: white;
                                padding: 35px;
                                border-radius: 12px;
                                text-align: center;
                                box-shadow: 0 5px 15px rgba(0,0,0,0.1);
                            }

                            .welcome h2 {
                                color: #2563eb;
                            }

                            .cards {
                                display: flex;
                                gap: 20px;
                                margin-top: 30px;
                                flex-wrap: wrap;
                            }

                            .card {
                                background: white;
                                flex: 1;
                                min-width: 200px;
                                padding: 25px;
                                border-radius: 12px;
                                box-shadow: 0 5px 15px rgba(0,0,0,0.08);
                            }

                            .card h3 {
                                color: #2563eb;
                            }

                            .status {
                                color: green;
                                font-weight: bold;
                            }

                            .tech {
                                margin-top: 30px;
                                background: #1e293b;
                                color: white;
                                padding: 25px;
                                border-radius: 12px;
                                text-align: center;
                            }

                            footer {
                                margin-top: 40px;
                                padding: 25px;
                                background: #111827;
                                color: white;
                                text-align: center;
                            }
                        </style>
                    </head>

                    <body>

                        <header>
                            <h1>CodeNest</h1>
                            <p>Java Maven Web Application</p>
                        </header>

                        <div class="container">

                            <div class="welcome">
                                <h2>Welcome to CodeNest! 🚀</h2>

                                <p>
                                    Your Java Maven application is
                                    successfully running.
                                </p>

                                <p>
                                    This application is running on a
                                    Java HTTP Server.
                                </p>
                            </div>

                            <div class="cards">

                                <div class="card">
                                    <h3>Server Status</h3>
                                    <p class="status">
                                        ● Server Running
                                    </p>
                                    <p>Port: 50050</p>
                                </div>

                                <div class="card">
                                    <h3>Java</h3>
                                    <p>
                                        Version: %s
                                    </p>
                                </div>

                                <div class="card">
                                    <h3>Maven</h3>
                                    <p>
                                        Project: CodeNest
                                    </p>
                                    <p>
                                        Build Tool: Maven
                                    </p>
                                </div>

                                <div class="card">
                                    <h3>Server Time</h3>
                                    <p>
                                        %s
                                    </p>
                                </div>

                            </div>

                            <div class="tech">
                                <h2>Technology Stack</h2>
                                <p>Java | Maven | Git | HTTP Server</p>
                            </div>

                        </div>

                        <footer>
                            CodeNest | Built with Java and Maven
                        </footer>

                    </body>
                    </html>
                    """.formatted(
                            javaVersion,
                            serverTime
                    );

            byte[] responseBytes = response.getBytes(
                    StandardCharsets.UTF_8
            );

            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/html; charset=UTF-8"
            );

            exchange.sendResponseHeaders(
                    200,
                    responseBytes.length
            );

            try (OutputStream output =
                         exchange.getResponseBody()) {

                output.write(responseBytes);
            }
        });

        server.start();

        System.out.println(
                "========================================"
        );

        System.out.println(
                "CodeNest is running successfully!"
        );

        System.out.println(
                "Port: 50050"
        );

        System.out.println(
                "Local: http://localhost:50050"
        );

        System.out.println(
                "========================================"
        );
    }
}