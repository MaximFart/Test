package ru.finplatforms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.finplatforms.controller.exception.NotFoundException;
import ru.finplatforms.model.Group;
import ru.finplatforms.service.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping()
    public String findAllGroups(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "group/groups";
    }
    @GetMapping("/{id}")
    public String getGroup(@PathVariable("id") Long id, Model model) throws Exception {
        model.addAttribute("group", groupService.findById(id).orElseThrow(NotFoundException::new));
        return "group/show";
    }
    @GetMapping("/new")
    public String createGroup(@ModelAttribute("group") Group group) {
        return "group/new";
    }

    @PostMapping()
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupService.save(group);
        return "redirect:/groups";
    }

    @GetMapping("/{id}/edit")
    public String editGroup(Model model, @PathVariable("id") Long id) throws Exception {
        model.addAttribute("group", groupService.findById(id).orElseThrow(NotFoundException::new));
        return "group/edit";
    }

    @PutMapping("/{id}")
    public String updateGroup(@ModelAttribute("group") Group group, @PathVariable("id") int id) {
        groupService.update(group);
        return "redirect:/groups";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable("id") Long id) {
        groupService.delete(id);
        return "redirect:/groups";
    }
}