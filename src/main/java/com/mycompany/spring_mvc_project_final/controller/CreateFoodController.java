package com.mycompany.spring_mvc_project_final.controller;





import com.mycompany.spring_mvc_project_final.domainModel.FoodDto;
import com.mycompany.spring_mvc_project_final.domainModel.IngredientDto;
import com.mycompany.spring_mvc_project_final.domainModel.LabelDto;
import com.mycompany.spring_mvc_project_final.dto.FoodRequest;
import com.mycompany.spring_mvc_project_final.dto.IngredientRequest;
import com.mycompany.spring_mvc_project_final.dto.LabelRequest;
import com.mycompany.spring_mvc_project_final.entities.LabelEntity;
import com.mycompany.spring_mvc_project_final.repository.IngredientRepository;
import com.mycompany.spring_mvc_project_final.repository.LabelRepository;
import com.mycompany.spring_mvc_project_final.service.CreateFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CreateFoodController {

    @Autowired
    CreateFoodService createFoodService;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private LabelRepository labelRepository;

    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
    public @ResponseBody List<String> autocomplete(@RequestParam("query") String query) {
        return createFoodService.getListOfIngredients(query);
    }
    @RequestMapping(value = "/labelAutocomplete", method = RequestMethod.GET)
    public @ResponseBody List<String> labelAutocomplete(@RequestParam("query1") String query1) {
        return createFoodService.getListOfLabels(query1);
    }

    @GetMapping(path = "/createForm")
    public String showAddForm(Model model) {
        List<String> allLabels=createFoodService.getAllLabelName();
        FoodRequest foodRequest = new FoodRequest();
        foodRequest.setIngredientRequests(new ArrayList<>());
        foodRequest.setLabelRequests(new ArrayList<>());
        model.addAttribute("foodRequest", foodRequest);
        model.addAttribute("allLabels", allLabels);
        return "create";
    }

    @PostMapping(value = "/createFood")
    public String createFood(@ModelAttribute(value = "foodRequest") FoodRequest foodRequest
    ) throws UnsupportedEncodingException {
        if (foodRequest.getIngredientRequests() == null) {
            foodRequest.setIngredientRequests(new ArrayList<>());
        }
        if (foodRequest.getLabelRequests() == null) {
            foodRequest.setLabelRequests(new ArrayList<>());
        }
        List<IngredientRequest> ingredientRequestList = foodRequest.getIngredientRequests();
        List<LabelRequest> labelRequestList = foodRequest.getLabelRequests();
        for (int i=0; i<labelRequestList.size();i++){
            if(labelRequestList.get(i).getName()==null){
                labelRequestList.remove(labelRequestList.get(i));
            }
        }
        for (int i=0; i<ingredientRequestList.size();i++){
            for (int j=i+1; j<ingredientRequestList.size();j++){
                if(ingredientRequestList.get(j).getName().equals(ingredientRequestList.get(i).getName())){
                    ingredientRequestList.get(i).setAmount(ingredientRequestList.get(i).getAmount()+ingredientRequestList.get(j).getAmount()*ingredientRequestList.get(j).getMeasureCo()/ingredientRequestList.get(i).getMeasureCo());
                    ingredientRequestList.remove(ingredientRequestList.get(j));
                }
            }
        }
        byte[] bytes1 = foodRequest.getName().getBytes(StandardCharsets.ISO_8859_1);
        String foodName = new String(bytes1, StandardCharsets.UTF_8);
        byte[] bytes2 = foodRequest.getDescription().getBytes(StandardCharsets.ISO_8859_1);
        String foodDes = new String(bytes2, StandardCharsets.UTF_8);
        byte[] bytes3 = foodRequest.getTutorial().getBytes(StandardCharsets.ISO_8859_1);
        String foodTur = new String(bytes3, StandardCharsets.UTF_8);
        FoodDto foodDto = new FoodDto(foodName, foodRequest.getImage(), foodDes, foodTur);
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        List<LabelDto> labelDtos = new ArrayList<>();

        for (IngredientRequest ingredientRequest : ingredientRequestList) {
            if(ingredientRequest!=null){
                byte[] bytes = ingredientRequest.getName().getBytes(StandardCharsets.ISO_8859_1);
                String encodeName = new String(bytes, StandardCharsets.UTF_8);
                ingredientDtos.add(new IngredientDto(encodeName, ingredientRequest.getAmount(), ingredientRequest.getMeasure()));
            }


        }
        for (LabelRequest labelRequest : labelRequestList) {
            byte[] bytes = labelRequest.getName().getBytes(StandardCharsets.ISO_8859_1);
            String encodeName = new String(bytes, StandardCharsets.UTF_8);
            labelDtos.add(new LabelDto(encodeName));

        }

        foodDto.setIngredientDtoList(ingredientDtos);
        foodDto.setLabelDtoList(labelDtos);
        createFoodService.createFood(foodDto);

        return "redirect:/createForm";
    }


}

