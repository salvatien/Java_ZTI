package zti.api.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import zti.api.springboot.service.*;
import zti.api.springboot.jpa.*;
import zti.api.springboot.model.AnswerPojo;
import zti.api.springboot.model.ModePojo;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ModeController {

    @Autowired
    ModeService modeService;

    @RequestMapping(value = "/modes", method = RequestMethod.GET)
    public List<ModePojo> getModes() {
    	List<ModePojo> modePojos = new ArrayList<>();
        List<Mode> modes =  modeService.getModes();
        for(Mode mode : modes) {
        	ModePojo modePojo = new ModePojo(mode);
        	modePojos.add(modePojo);
        }
        return modePojos;
    }
    

    @RequestMapping(value = "/modes/{modeId}", method = RequestMethod.GET)
    public ModePojo getModeById(@PathVariable(value = "modeId") Long modeId) {
        try {
			return new ModePojo(modeService.getModeById(modeId).get());
		} catch (Exception e) {
			return new ModePojo(null);
		}
    }
}