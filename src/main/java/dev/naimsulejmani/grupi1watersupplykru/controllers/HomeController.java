package dev.naimsulejmani.grupi1watersupplykru.controllers;


import dev.naimsulejmani.grupi1watersupplykru.services.impls.MeterServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final MeterServiceImpl meterService;

    public HomeController(MeterServiceImpl meterService) {
        this.meterService = meterService;
    }

    @GetMapping("")
    public String home(HttpServletRequest request, HttpServletResponse response) {

//        Cookie[] cookies = request.getCookies();
//
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                System.out.println(cookie.getName() + " : " + cookie.getValue());
//                if (cookie.getName().equals("ticketId")) {
//                    return "redirect:/customers";
//                }
//            }
//        }

//        Meter meter = new Meter();
//        meter.setSerialNo("" + new Random().nextInt(1_000_000_000, 2_000_000_000));
//        meter.setMeterType(MeterType.RESIDENTIAL);
//        meter.setLocation("Te bunari i hajratit");
//        meter.setInstallationDate(LocalDate.now());
//        meter.setLastReading(5.5);
//
//        this.meterService.save(meter);

//        Cookie cookie = new Cookie("ticketId"
//                , String.valueOf(new Random().nextInt(1, 2_000_000_000)));
//        cookie.setMaxAge(20);
//        cookie.setPath("/");
//        cookie.setDomain("localhost");
//        cookie.setHttpOnly(true);
//        cookie.setSecure(false);
//
//        response.addCookie(cookie);

        return "index";
    }
}
