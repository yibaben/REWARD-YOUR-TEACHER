package com.example.rewardyourteachersq011bjavapode.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.rewardyourteachersq011bjavapode.exceptions.ResourceNotFoundException;
import com.example.rewardyourteachersq011bjavapode.exceptions.UserNotFoundException;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserUtil {
    private final Cloudinary cloudinary;

    private final UserRepository userRepository;
    private static File convertMultipartToFile(MultipartFile image) throws IOException {
        File convertedFile;
        String file = image.getOriginalFilename();
        assert file != null;
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            convertedFile = new File(file);
            fileOutputStream.write(image.getBytes());
        }
        return convertedFile;
    }

    public String uploadImage(MultipartFile image) throws IOException {
        try {
            File uploadedFile = convertMultipartToFile(image);
            Map uploadResult = cloudinary.uploader().upload(uploadedFile,
                    ObjectUtils.asMap("use filename", true, "unique-filename", true));
            boolean isDeleted = uploadedFile.delete();
            if (isDeleted)
                log.info("File Deleted Successfully");
            else
                log.info("File Does not exist");
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public User getUserByEmail(String email){
        return  userRepository.findUserByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("User with " + email + " not found"));
    }

    public String getAuthenticatedUserEmail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

}
