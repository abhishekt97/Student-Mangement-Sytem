package com.abhishek.sms.utils.messages;

public class ErrorMessages {


    private ErrorMessages(){}


    //user
    public static final String NOT_HAVE_EXPECTED_ROLE_USER = "Error: User does not have expected role";
    public static final String NOT_FOUND_USER = "Error: User not found with id %s";
    public static final String NOT_FOUND_USER_USERNAME = "Error: User not found with username %s";
    public static final String NOT_PERMITTED_METHOD = "You do not have any permission to do this operation";
    public static final String PASSWORD_SHOULD_NOT_MATCHED = "Your passwords are not matched" ;

    //user roles
    public static final String ROLE_NOT_FOUND = "There is no role like that, check the database";
    public static final String NOT_FOUND_USER_USER_ROLE= "Error: User not found with user-role %s";

    //unique properties
    public static final String ALREADY_REGISTERED_USERNAME = "Error: User with username %s is already registered";
    public static final String ALREADY_REGISTERED_PHONE_NUMBER = "Error: User with phone number %s is already registered";
    public static final String ALREADY_REGISTERED_EMAIL = "Error: User with email %s is already registered";
    public static final String ALREADY_REGISTERED_NAME = "Error: name %s is already registered";


    //education term
    public static final String EDUCATION_START_DATE_IS_EARLIER_THAN_LAST_REGISTRATION_DATE = "Error: The start date cannot be earlier than the last registration date " ;
    public static final String EDUCATION_END_DATE_IS_EARLIER_THAN_START_DATE = "Error: The end date cannot be earlier than the start date " ;
    public static final String EDUCATION_TERM_IS_ALREADY_EXIST_BY_TERM_AND_YEAR_MESSAGE = "Error: Education Term with Term And Year already exist " ;
    public static final String EDUCATION_TERM_NOT_FOUND_MESSAGE = "Error: Education Term with id %s not found" ;
    public static final String EDUCATION_TERM_CONFLICT_MESSAGE = "Error: There is a conflict regarding the dates of the education terms.";

    //lesson
    public static final String ALREADY_REGISTER_LESSON_MESSAGE = "Error: Lesson with lesson name %s already registered" ;
    public static final String ALREADY_CREATED_LESSON_MESSAGE = "Error: %s Lesson already exist";
    public static final String NOT_FOUND_LESSON_MESSAGE = "Error: Lesson with id %s not found";
    public static final String NOT_FOUND_LESSON_IN_LIST = "Error: Lesson not found in the list" ;
    public static final String TIME_NOT_VALID_MESSAGE = "Error: incorrect time" ;

    //lesson program
    public static final String NOT_FOUND_LESSON_PROGRAM_MESSAGE = "Error: Lesson program with id, %s not found";
    public static final String NOT_FOUND_LESSON_PROGRAM_MESSAGE_WITHOUT_ID_INFO = "Error: Lesson program with this field not found";
    public static final String LESSON_PROGRAM_ALREADY_EXIST = "Error: Course schedule can not be selected for the same hour and date" ;


    //advisor teacher
    public static final String NOT_FOUND_ADVISOR_MESSAGE = "Error: Advisor Teacher with id %s not found" ;
    public static final String ALREADY_EXIST_ADVISOR_MESSAGE = "Error: Advisor Teacher with id %s is already exist";
    public static final String NOT_EXIST_ADVISOR_MESSAGE = "Error: Advisor Teacher with id %s isn't exist";
    public static final String NOT_FOUND_ADVISOR_MESSAGE_WITH_USERNAME = "Error: Advisor Teacher with username %s not found" ;

    //student

    public static final String NOT_FOUND_STUDENT_MESSAGE = "Error: Student with id %s not found" ;
    public static final String NOT_FOUND_USER_WITH_ROLE_MESSAGE = "Error: The role information of the user with id %s is not role: %s" ;
    public static final String NOT_FOUND_STUDENT_BY_USERNAME_MESSAGE = "Error: Student with username %s not found" ;
    public static final String STUDENT_INFO_NOT_FOUND = "Error: Student Info with id %d not found" ;
    public static final String STUDENT_INFO_NOT_FOUND_BY_NAME = "Error: Student Info with for name %s not found" ;
    public static final String STUDENT_INFO_NOT_FOUND_BY_STUDENT_ID= "Error: Student Info with  student id %d not found" ;

    //Teacher

    public static final String NOT_FOUND_TEACHER = "Error: Teacher with id %s not found" ;
    public static final String NOT_FOUND_TEACHER_BY_NAME = "Error: Teacher with name %s not found" ;


    //Academic Year
    public static final String NOT_FOUND_ACADEMIC_YEAR = "Error: Academic year with name %s not found" ;
    public static final String NOT_FOUND_ACADEMIC_YEAR_ID = "Error: Academic year with id %s not found" ;

    //Class Level
    public static final String NOT_FOUND_CLASS_LEVEL = "Error: Class level with name %s not found.";

    //Exam
    public static final String NOT_FOUND_EXAM = "Error: Exam with name %s not found.";
    public static final String NOT_FOUND_EXAM_ID = "Error: Exam with id %s not found.";
    public static final String ALREADY_EXIST_EXAM = "Error: Exam with name %s is already exist";

    //Exam Result

    public static final String NOT_FOUND_EXAM_RESULT = "Error: Exam result with id %s not found.";
    public static final String ALREADY_EXIST_EXAM_RESULT = "Error: Exam result with name %s is already exist";

    //Question
    public static final String NOT_FOUND_QUESTION = "Error: Question with id %s not found.";

    //Subject
    public static final String NOT_FOUND_SUBJECT = "Error: Subject with name %s not found.";
    public static final String NOT_FOUND_SUBJECT_BY_TEACHER_ID = "Error: Subject with teacher id %s not found.";
    public static final String ALREADY_EXIST_SUBJECT = "Error: Subject with name %s is already registered!";


    //Program
    public static final String NOT_FOUND_PROGRAM = "Error: Program with name %s not found.";
    public static final String NOT_FOUND_PROGRAM_FOR_STUDENT_ID = "Error: Program with student id %s not found.";
    public static final String ALREADY_EXIST_PROGRAM = "Error: Program with name %s is already registered!";

}
