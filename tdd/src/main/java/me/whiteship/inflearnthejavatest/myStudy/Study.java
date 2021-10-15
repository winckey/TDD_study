package me.whiteship.inflearnthejavatest.myStudy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;



@Getter
public class Study {


    private StudyStatus status = StudyStatus.OPENED;




}
