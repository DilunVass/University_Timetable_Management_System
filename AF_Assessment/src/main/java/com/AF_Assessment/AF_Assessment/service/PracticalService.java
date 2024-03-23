package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.dto.PracticalDTO;
import com.AF_Assessment.AF_Assessment.model.*;
import com.AF_Assessment.AF_Assessment.repository.LabRepository;
import com.AF_Assessment.AF_Assessment.repository.LecturerRepository;
import com.AF_Assessment.AF_Assessment.repository.PracticalRepository;
import com.AF_Assessment.AF_Assessment.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PracticalService {

    @Autowired
    private PracticalRepository practicalRepository;

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    public List<Practical> getAllPracticals(){
        return practicalRepository.findAll();
    }

    public Practical addPractical(PracticalDTO dto) {
        Practical result = null;
        Optional<Lecturer> exLecturer = lecturerRepository.findLecturerByEmail(dto.getLecturer());
        Optional<Subjects> exSub = subjectsRepository.findByName(dto.getSubject());
        Optional<Lab> exLab = labRepository.findLabByLabCode(dto.getLab());

        try {
            if (exLecturer.isPresent()) {
                if (exSub.isPresent()) {
                    if (exLab.isPresent()) {

                        LocalDate today = LocalDate.now();

                        Practical practical = map(dto);
                        if (practical.getDate().isAfter(today)) {
                            if (dto.getDuration() > 0 && dto.getDuration() < 10) {
                                practicalRepository.save(practical);

                                dto.setId(result.get_id());
                                result = practical;
                            }

                        } else {
                            throw new RuntimeException("Can't set practical in the past");
                        }

                    } else {
                        throw new RuntimeException("Hall is not exist");
                    }
                } else {
                    throw new RuntimeException("Subject is not exist");
                }
            } else {
                throw new RuntimeException("Lecturer is not exist");
            }

        } catch (Exception e) {
            return null;
        }

        return result;
    }

    private Practical map(PracticalDTO dto){
        return Practical.builder()
                .date(dto.getDate())
                .startTime(String.valueOf(dto.getStartTime()))
                .duration(dto.getDuration())
                .subject(dto.getSubject())
                .lab(dto.getLab())
                .lecturer(dto.getLecturer())
                .build();
    }

}
