package org.tarak.pms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarak.pms.models.Division;
import org.tarak.pms.services.ServiceInterface;

/**
 * Created by Tarak on 12/4/2016.
 */

@RequestMapping("/division")
@Controller
public class DivisionController {

    @Autowired
    private ServiceInterface<Division, Integer> divisionService;

    @RequestMapping("/")
    public String index(Model model)
    {
    	prepareModel(model);
        return "division/index";
    }

    private void prepareModel(Model model) 
    {
    	if(!model.containsAttribute("division"))
    	{
            model.addAttribute("division", new Division());    		
    	}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST )
    public String addDivision(@Valid Division division, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
    		return "division/index";
        }
        try
        {
        	divisionService.saveAndFlush(division);
        }
        catch(DataIntegrityViolationException e)
        {
        	String args[]={"Division",division.getName()};
        	bindingResult.rejectValue("name", "error.alreadyExists",args ,"Division with name "+division.getName()+" already exists");
        	return "division/index";
        }
        catch(Exception e)
        {
        	String args[]={"Division",division.getName()};
        	bindingResult.rejectValue("name", "error.alreadyExists",args ,"Unknown error! Please contact Administrator");
        	return "division/index";
        }
        model.addAttribute("division", new Division());
        return "division/index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET )
    public @ResponseBody
    List<Division> listCategories()
    {
        List<Division> list=divisionService.findAll();
        return list;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET )
    public String deleteDivision(@PathVariable Integer id, Model model)
    {
    	
    	divisionService.delete(id);
    	return index(model);
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editDivision(@PathVariable Integer id, Model model)
    {
    	Division division=divisionService.findOne(id);
    	model.addAttribute("division", division);
    	return "division/edit";
    }
   
}
