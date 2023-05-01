package pro.sky.homework.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.homework.exception.IncorrectNameException;

@Service
public class ValidatorService {
    public String validateName(String nameEmployee) {
        if (!StringUtils.isAlpha(nameEmployee)) {
            throw new IncorrectNameException();
        }
        return StringUtils.capitalize(nameEmployee.toLowerCase());
    }

    public String validateSurName(String surNameEmployee) {
        if (!StringUtils.isAlpha(surNameEmployee)) {
            throw new IncorrectNameException();
        }
        return StringUtils.capitalize(surNameEmployee.toLowerCase());
    }
}
