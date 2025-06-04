package boldair.web;

import java.io.IOException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FichierController {

    private final JdbcTemplate jdbcTemplate;

    public FichierController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/certificat/{id}")
    public void afficherCertificat(@PathVariable Long id, HttpServletResponse response) throws IOException {
        String sql = "SELECT certificat_med FROM participant WHERE id_part = ?";
        byte[] fichier = jdbcTemplate.queryForObject(sql, new Object[]{id}, byte[].class);

        if (fichier == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/pdf"); // ou "image/png", selon le format réel
        response.setContentLength(fichier.length);
        response.getOutputStream().write(fichier);
    }
}
