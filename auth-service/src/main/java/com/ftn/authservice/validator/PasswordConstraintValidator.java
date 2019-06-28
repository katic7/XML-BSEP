package com.ftn.authservice.validator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.io.FileUtils;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

import com.google.common.base.Joiner;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
	 
    @Override
    public void initialize(ValidPassword arg0) {
    }
 
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
           new LengthRule(8, 30), 
           new UppercaseCharacterRule(1), 
           new DigitCharacterRule(1), 
           new SpecialCharacterRule(1), 
//           new NumericalSequenceRule(3,false), 
//           new AlphabeticalSequenceRule(3,false), 
//           new QwertySequenceRule(3,false),
           new WhitespaceRule()));
        
        List<String> lines = new ArrayList<>();	
        
        try {
			lines = FileUtils.readLines(new File("src/main/resources/forbidden_passwords.txt"), "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        for(int i = 0; i < lines.size(); i++) {
        	System.out.println(lines.get(i));
        }
 
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
        	if(!lines.contains(password))
        		return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
          Joiner.on(",").join(validator.getMessages(result)))
          .addConstraintViolation();
        return false;
    }
}