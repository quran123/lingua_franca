package com.anushka.lingua_franca;

public class quizhandler {
        public String question;
        public String answer;
        public String number;
        public String option1;
        public String option2;
        public String option3;
        public String option4;

        // Default constructor required for calls to
        // DataSnapshot.getValue(User.class)
        public quizhandler() {
        }

        public quizhandler(String question, String answer,String number) {
            this.answer = answer;
            this.question = question;
            this.number=number;
        }
        public String getQuestion(){
            return question;
        }
        public String getAnswer(){
            return answer;
        }
        public String getNumber(){
            return number;
        }
        public String getOption1(){
            return option1;
        }
        public String getOption2(){
            return option2;
        }
        public String getOption3(){
            return option3;
        }
        public String getOption4(){
            return option4;
        }
        public void setQuestion(String question){
            this.question= question;
        }
        public void setAnswer(String answer){
            this.answer=answer;
        }
        public void setNumber(String number){
            this.number=number;
        }
        public void setOption1(String option1){
            this.option1=option1;
        }
        public void setOption2(String option2){
            this.option2=option2;
        }
        public void setOption3(String option3){
            this.option3=option3;
        }
        public void setOption4(String option4){
            this.option4=option4;
        }
}
