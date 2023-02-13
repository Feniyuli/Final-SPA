package de.dhbw.dinnerfortwo.api


import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.view.RedirectView

/**
 * Home redirection to swagger api documentation
 */
@Controller
class HomeController {
    @GetMapping("/")
    fun home(): RedirectView {
        return RedirectView("/swagger-ui.html")
    }
}
