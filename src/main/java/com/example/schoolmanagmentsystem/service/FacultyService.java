package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.FacultyDTO;
import com.example.schoolmanagmentsystem.entity.Faculty;
import com.example.schoolmanagmentsystem.exception.DuplicateDataException;
import com.example.schoolmanagmentsystem.repository.FacultyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Transactional
    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        if (facultyRepository.existsByFacultyName(facultyDTO.getFacultyName())) {
            throw new DuplicateDataException("Tên khoa '" + facultyDTO.getFacultyName() + "' đã tồn tại!");
        }

        if (facultyRepository.existsByEmail(facultyDTO.getEmail())) {
            throw new DuplicateDataException("Email '" + facultyDTO.getEmail() + "' đã tồn tại!");
        }
        if (facultyRepository.existsByPhone(facultyDTO.getPhone())) {
            throw new DuplicateDataException("SĐT '" + facultyDTO.getPhone() + "' đã tồn tại!");
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
        // findFacultyById bây giờ sẽ ném 404 nếu không tìm thấy
        Faculty faculty = findFacultyById(id);
        return convertToDTO(faculty);
    }


    private Faculty findFacultyById(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khoa với ID: " + id));
    }

    @Transactional // Thêm @Transactional cho nghiệp vụ update
    public FacultyDTO updateFaculty(Long id, FacultyDTO facultyDTO) {

        Faculty existingFaculty = findFacultyById(id);

        if (facultyDTO.getFacultyName() != null &&
                !Objects.equals(existingFaculty.getFacultyName(), facultyDTO.getFacultyName()) &&
                facultyRepository.existsByFacultyName(facultyDTO.getFacultyName())) {
            throw new DuplicateDataException("Tên khoa '" + facultyDTO.getFacultyName() + "' đã tồn tại!");
        }

        if (!Objects.equals(existingFaculty.getEmail(), facultyDTO.getEmail()) &&
                facultyRepository.existsByEmailAndFacultyIdNot(facultyDTO.getEmail(), id )) {
            throw new DuplicateDataException("Email '" + facultyDTO.getEmail() + "' đã tồn tại!");
        }

        if (!Objects.equals(existingFaculty.getPhone(), facultyDTO.getPhone()) &&
                facultyRepository.existsByPhoneAndFacultyIdNot(facultyDTO.getPhone(), id)) {
            throw new DuplicateDataException("SĐT '" + facultyDTO.getPhone() + "' đã tồn tại!");
        }

        existingFaculty.setFacultyName(facultyDTO.getFacultyName());
        existingFaculty.setDean(facultyDTO.getDean());
        existingFaculty.setPhone(facultyDTO.getPhone());
        existingFaculty.setEmail(facultyDTO.getEmail());
        existingFaculty.setDescription(facultyDTO.getDescription());
        existingFaculty.setAddress(facultyDTO.getAddress());

        Faculty updatedFaculty = facultyRepository.save(existingFaculty);
        return convertToDTO(updatedFaculty);
    }

    @Transactional
    public FacultyDTO deleteFaculty(Long id) {
        Faculty faculty = findFacultyById(id);
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
