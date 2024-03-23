package com.AF_Assessment.AF_Assessment.service;

import com.AF_Assessment.AF_Assessment.dto.LectureDTO;
import com.AF_Assessment.AF_Assessment.model.Lecture;
import com.AF_Assessment.AF_Assessment.model.LectureHall;
import com.AF_Assessment.AF_Assessment.model.Lecturer;
import com.AF_Assessment.AF_Assessment.model.Subjects;
import com.AF_Assessment.AF_Assessment.repository.LectureHallRepository;
import com.AF_Assessment.AF_Assessment.repository.LectureRepository;
import com.AF_Assessment.AF_Assessment.repository.LecturerRepository;
import com.AF_Assessment.AF_Assessment.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private SubjectsRepository subjectsRepository;
    @Autowired
    private LectureHallRepository lectureHallRepository;

    public List<Lecture> getAllLectures(){
        return lectureRepository.findAll();
    }

//    public Lecture addLecture(LectureDTO dto){
//        Lecture result = null;
//        try{
//            Optional<Lecturer> existingLecturer = lecturerRepository.findBy_id(dto.getLecturer());
//            Optional<Subjects> existingSubject = subjectsRepository.findByName(dto.getSubject());
//
//            Lecture lecture = map(dto);
//            lectureRepository.save(lecture);
//
//            dto.setId(result.get_id());
//            result = lecture;
////            if (existingLecturer.isPresent() && existingSubject.isPresent()){
////
////            }
//
//
////            if (existingLecturer.isPresent() && existingSubject.isPresent()){
////
////                if (dto.getDuration() < 10 && dto.getDuration() > 0){
////
////                    LocalDate today = LocalDate.now();
////
////                    if (lecture.getDate().isBefore(today)){
////
////                    }else {
////
////
////                        return result;
////                    }
////                }
////            }
//        }catch (Exception e){
//            return null;
//        }
//        return result;
//    }
//
//    private Lecture map(LectureDTO dto){
//        try {
////            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
////            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
////
////            LocalDate date;
////            LocalTime time;
////
////            try{
////                date = LocalDate.parse(dto.getDate(),dateTimeFormatter);
////                time = LocalTime.parse(dto.getStartTime(), timeFormatter);
////            }catch (Exception e){
////                throw new RuntimeException("Invalid date/time format");
////            }
//
//            return Lecture.builder()
//                    .date(dto.getDate())
//                    .startTime(dto.getStartTime())
//                    .duration(dto.getDuration())
//                    .subject(dto.getSubject())
//                    .lecturer(dto.getLecturer())
//                    .lectureHall(dto.getLectureHall())
//                    .build();
//        }catch (Exception e){
//            return null;
//        }
//    }

    public Lecture addLecture(LectureDTO dto){
        Lecture result = null;
        Optional<Lecturer> exLecturer = lecturerRepository.findLecturerByEmail(dto.getLecturer());
        Optional<Subjects> exSub = subjectsRepository.findByName(dto.getSubject());
        Optional<LectureHall> exHall = lectureHallRepository.findLectureHallByHallCode(dto.getLectureHall());
        try{



            if (exLecturer.isPresent()){
                if (exSub.isPresent()){
                    if (exHall.isPresent()){

                        LocalDate today = LocalDate.now();

                        Lecture lecture = map(dto);
                        if (lecture.getDate().isAfter(today)){
                            if (dto.getDuration()>0 && dto.getDuration() < 10){
                                lectureRepository.save(lecture);

                                dto.setId(result.get_id());
                                result = lecture;
                            }

                        }else {
                            throw new RuntimeException("Can't set lecture in the past");
                        }

                    }else {
                        throw new RuntimeException("Hall is not exist");
                    }
                }else {
                    throw new RuntimeException("Subject is not exist");
                }
            }else {
                throw new RuntimeException("Lecturer is not exist");
            }

        }catch (Exception e){
            return null;
        }
        return null;
    }

    private Lecture map(LectureDTO dto){
        return Lecture.builder()
                .date(dto.getDate())
                .startTime(String.valueOf(dto.getStartTime()))
                .duration(dto.getDuration())
                .subject(dto.getSubject())
                .lectureHall(dto.getLectureHall())
                .lecturer(dto.getLecturer())
                .build();
    }

}
