package hyh.controller;

import hyh.action.FileAction;
import hyh.action.UserAction;
import hyh.entity.UserFile;
import hyh.service.UserFileService;
import hyh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;

    @RequestMapping("/editfile")
    public String EditFile(String filename, String tag,
                           String type, String summary, String id){
        if (!UserAction.checkNull(filename, tag, type, summary, id)){
            return "error";
        }

        UserFile file;

        try {
            file = userfileservice.getById(Long.valueOf(id));
        }catch (Exception e){
            return "error";
        }

        if (file == null){
            log.error("Excetional update");
            return "notfound";
        }

        file.setFilename(filename);
        file.setTag(tag);
        file.setType(FileAction.getType(type));
        file.setSummary(summary);

        if (userfileservice.update(file) == 1){
            return "done";
        }

        return "error";
    }

    @RequestMapping("/deletefile")
    public int deleteFile(String id){
        return userfileservice.deleteById(Long.valueOf(id));
    }
}
