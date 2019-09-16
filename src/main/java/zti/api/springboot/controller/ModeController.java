package zti.api.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import zti.api.springboot.service.*;
import zti.api.springboot.jpa.*;

import java.util.List;


@RestController
public class ModeController {

    @Autowired
    ModeService modeService;

    @RequestMapping(value = "/modes", method = RequestMethod.GET)
    public List<Mode> getModes() {
        return modeService.getModes();
    }
    

    @RequestMapping(value = "/modes/{modeId}", method = RequestMethod.GET)
    public Mode getModeById(@PathVariable(value = "modeId") Long modeId) {
        try {
			return modeService.getModeById(modeId).get();
		} catch (Exception e) {
			return new Mode();
		}
    }
}
