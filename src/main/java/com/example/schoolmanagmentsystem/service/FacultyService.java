package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.FacultyDTO;
import com.example.schoolmanagmentsystem.entity.Faculty;
import com.example.schoolmanagmentsystem.exception.DuplicateDataException;
import com.example.schoolmanagmentsystem.repository.FacultyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Transactional
    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {

        if (facultyRepository.existsByFacultyName(facultyDTO.getFacultyName())) {
            throw new DuplicateDataException("Tên khoa đã tồn tại trong hệ thống!");
        }

        Faculty faculty = convertToEntity(facultyDTO);
        Faculty savedFaculty = facultyRepository.save(faculty);
        return convertToDTO(savedFaculty);
    }

    public List<FacultyDTO> getAllFaculty() {
        return facultyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FacultyDTO getFacultyById(Long id) {
        Faculty faculty = findFacultyById(id);
        return convertToDTO(faculty);
    }

    private Faculty findFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);

    }

    public FacultyDTO updateFaculty(Long id, FacultyDTO facultyDTO) {
        Faculty Faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoa!"));

        Faculty.setFacultyName(facultyDTO.getFacultyName());
        Faculty.setDean(facultyDTO.getDean());
        Faculty.setPhone(facultyDTO.getPhone());
        Faculty.setEmail(facultyDTO.getEmail());
        Faculty updatedFaculty = facultyRepository.save(Faculty);
        return convertToDTO(updatedFaculty);
    }

    public FacultyDTO deleteFaculty(Long id) {
        Faculty faculty = findFacultyById(id);
        if (!facultyRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy khoa với ID: " + id);
        }
        facultyRepository.delete(faculty);
        return convertToDTO(faculty);
    }

    private Faculty convertToEntity(FacultyDTO dto) {
        Faculty faculty = new Faculty();
        faculty.setFacultyName(dto.getFacultyName());
        faculty.setDean(dto.getDean());
        faculty.setPhone(dto.getPhone());
        faculty.setEmail(dto.getEmail());
        faculty.setDescription(dto.getDescription());
        faculty.setAddress(dto.getAddress());
        return faculty;
    }

    private FacultyDTO convertToDTO(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        dto.setFacultyId(faculty.getFacultyId());
        dto.setFacultyName(faculty.getFacultyName());
        dto.setDean(faculty.getDean());
        dto.setPhone(faculty.getPhone());
        dto.setEmail(faculty.getEmail());
        dto.setDescription(faculty.getDescription());
        dto.setAddress(faculty.getAddress());
        return dto;
    }
}
