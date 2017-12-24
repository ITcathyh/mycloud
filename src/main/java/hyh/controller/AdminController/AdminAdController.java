package hyh.controller.AdminController;

import hyh.action.FileAction;
import hyh.action.UserAction;
import hyh.entity.Advertisement;
import hyh.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@RestController
@RequestMapping("/admin")
public class AdminAdController {
    @Autowired
    private AdvertisementService advertisementservice;
    private static final String IMAMG_PATH = "/adimag/";

    @RequestMapping("/setad")
    public String setAd(@RequestParam("file") CommonsMultipartFile image,
                        HttpServletRequest request) {
        Advertisement ad = dealAd(image, request);

        if (ad != null && advertisementservice.add(ad) == 1) {
            return "done";
        }

        return "error";
    }

    @RequestMapping("/deletead")
    public int deleteAd(short id) {
        return advertisementservice.deleteById(id);
    }

    private Advertisement dealAd(CommonsMultipartFile image,
                                 HttpServletRequest request) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        String href, deadline;

        href = mRequest.getParameter("href");
        deadline = mRequest.getParameter("deadline");

        if (!UserAction.checkNull(href, deadline)) {
            return null;
        }

        mRequest = null;
        Advertisement ad = new Advertisement();

        ad.setDeadline(Timestamp.valueOf(deadline));
        ad.setHref(href);
        ad.setImgpath(IMAMG_PATH + image.getOriginalFilename());

        if (FileAction.transferFile(IMAMG_PATH, image)) {
            return ad;
        }

        return null;
    }
}
