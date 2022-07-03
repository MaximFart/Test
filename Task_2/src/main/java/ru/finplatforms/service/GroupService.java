package ru.finplatforms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.finplatforms.model.Group;
import ru.finplatforms.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void save(Group group) {
        groupRepository.save(group);
    }

    public Optional<Group> findById(Long id) throws Exception {
        return groupRepository.findById(id);
    }

    public void update(Group group) {
        groupRepository.save(group);
    }

    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }
}
