class Student:
    def __init__(self,student_nr, first_name, last_name):
        self.student_nr=student_nr
        self.first_name=first_name
        self.last_name=last_name

    def set_name(self,first_name,last_name):
        self.first_name=first_name
        self.last_name=last_name

    def __str__(self):
        return self.first_name+" "+self.last_name+", s"+self.student_nr
