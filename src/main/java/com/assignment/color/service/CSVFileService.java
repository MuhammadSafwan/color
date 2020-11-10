package com.assignment.color.service;

import com.assignment.color.model.Color;
import com.assignment.color.model.Person;
import com.assignment.color.repository.PersonRepository;
import com.assignment.color.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Safwan
 */
@Service
public class CSVFileService implements FileService {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private PersonRepository personRepository;

    List<String> records = new ArrayList<>();

    @Override
    public void readFileAndSaveData(String path) {
        Resource resource = resourceLoader.getResource(path); //"classpath:sample-input.csv"
        try {
            FileInputStream fis = new FileInputStream(resource.getFile());
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                char character = line.charAt(line.length() - 1);
                if(character == ',') {
                    line += br.readLine();
                }
                records.add(line);
            }
            br.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        saveData(records);
    }

    private void saveData(List<String> records) {
        for(int i = 0; i < records.size(); i++) {
            Person person = new Person();
            Color color = new Color();
            String[] values = records.get(i).split(",");
            String address = values[2].trim();
            Integer zipCode = Integer.parseInt(address.substring(0, 5));
            String city = address.substring(6);
            person.setFirstName(values[0]);
            person.setLastName(values[1]);
            person.setZipCode(zipCode);
            person.setCity(city);
            color.setColorId(Integer.parseInt(values[3].trim()));
            person.setColor(color);
            personRepository.save(person);
        }
    }
}
