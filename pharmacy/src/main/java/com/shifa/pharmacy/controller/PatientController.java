package com.shifa.pharmacy.controller;

import com.shifa.pharmacy.model.DrugManufacturer;
import com.shifa.pharmacy.model.Patient;
import com.shifa.pharmacy.model.Role;
import com.shifa.pharmacy.model.User;
import com.shifa.pharmacy.model.viewmodels.RegisterUserModel;
import com.shifa.pharmacy.repository.PatientRepository;
import com.shifa.pharmacy.repository.RoleRepository;
import com.shifa.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {

    final
    UserRepository userRepository;

    final
    PasswordEncoder passwordEncoder;

    final
    RoleRepository roleRepository;

    final
    PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/patients/list")
    public String patients(Model model) {
        model.addAttribute("patients", userRepository.findUsersByJobTittle("Patient"));
        return "patient/list";
    }

    @GetMapping("/patients/create")
    public String createPatients(Model model) {
        return "patient/create";
    }

    @PostMapping(value = "/patients/register")
    public String register(Model model, RedirectAttributes redirectAttributes, RegisterUserModel registerUserModel) {
        //String regex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$";
        //Pattern p = Pattern.compile(regex);
        // Matcher m = p.matcher(registerUserModel.getPassword());
        if (!registerUserModel.getPassword().equals(registerUserModel.getConfirmPassword())) {
            redirectAttributes.addAttribute("error", "Password does not match ");
        } else if (userRepository.existsByUsername(registerUserModel.getUsername())) {
            redirectAttributes.addAttribute("error", "User with same id already exist ");
        } else if (registerUserModel.getPassword().isBlank() || registerUserModel.getPassword().isEmpty()) {
            redirectAttributes.addAttribute("error", "Password can not be empty or blank ");
        }

        //       else if( !m.matches()){
//            redirectAttributes.addAttribute("error","Password is not strong enough");
//        }
        else {
            User u = new User();
            u.setUsername(registerUserModel.getUsername());
            u.setPassword(passwordEncoder.encode(registerUserModel.getPassword()));
            Optional<Role> optionalRole = roleRepository.findByName("PATIENT");
            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                List<Role> roleList = new ArrayList<>();
                roleList.add(role);
                u.setRoles(roleList);
            }
            u.setJobTittle("Patient");
            u.setFirstName(registerUserModel.getFirstName());
            u.setLastName(registerUserModel.getLastName());
            u.setAge(registerUserModel.getAge());
            u.setAddress(registerUserModel.getAddress());
            u.setPhone(registerUserModel.getPhone());
            u.setEmail(registerUserModel.getEmail());
            userRepository.save(u);
            redirectAttributes.addAttribute("success", "You have successfully registered");
            //redirectAttributes.addAttribute("error","");
            return "redirect:/patients/create";
        }
        return "redirect:/patients/create";
    }


  /*  @RequestMapping(value = "/patients/create", method = RequestMethod.GET)
    public String create(Model model) {
        return "patient/create";
    }

    @RequestMapping(value = "/patients/add", method = RequestMethod.POST)
    public String add(Model model, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String gender, @RequestParam String address, @RequestParam String phoneNumber, @RequestParam String email) {

        Patient patient = new Patient(firstName, lastName, gender, address, phoneNumber, email);
        patientRepository.save(patient);
        return "redirect:/patients/list";
    }
*/


    @RequestMapping(value = "/patients/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {

        model.addAttribute("patient", patientRepository.findById(id).get());
        return "patient/edit";
    }


    @RequestMapping(value = "/patients/update", method = RequestMethod.POST)
    public String updatePatient(Model model, @RequestParam int id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String gender, @RequestParam String address, @RequestParam String phone, @RequestParam String email) {


        Patient patient = patientRepository.findById(id).get();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setGender(gender);
        patient.setAddress(address);
        patient.setPhone(phone);
        patient.setEmail(email);
        patientRepository.save(patient);

        return "redirect:/patients/list";
    }

    @RequestMapping(value = "/patients/delete/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") int id, Model model) {

        Patient patient = patientRepository.findById(id).get();

        patientRepository.delete(patient);
        return "redirect:/patients/list";
    }


}
