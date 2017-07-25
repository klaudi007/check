package kit.controller;

import kit.model.*;
import kit.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/public")
public class PublicController {

    private static Logger logger = LoggerFactory.getLogger(PublicController.class);


//	*******************************************************************
//	*************************      Check      *************************
//	*******************************************************************

    private CheckRepository checkRepository;

    public PublicController(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }
    @RequestMapping(value = "/check/save", method = RequestMethod.POST)
    public CheckWrapper addCheck(@RequestBody Check check){
        logger.info("request for saving data");
        CheckWrapper checkWrapper=new CheckWrapper();

        if(check.getAge()<18){
           checkWrapper.setStatus(1);
        }else if(check.getAge()>67){
            checkWrapper.setStatus(2);
        }
        else{
            checkWrapper.setStatus(3);
            checkRepository.save(check);
            checkWrapper.setCheck(checkRepository.findAll());
        }

        return checkWrapper;
    }
    @RequestMapping("/check/all")
    public List<Check> findAllCheck(){
        logger.info("request for getting all data");
        return checkRepository.findAll();
    }
    @RequestMapping(value = "/check/delete/{eid}", method = RequestMethod.POST)
    public List<Check> deleteCheck(@PathVariable Long eid){
        logger.info("request for deleting data");
        checkRepository.delete(eid);
        return checkRepository.findAll();
    }

}
