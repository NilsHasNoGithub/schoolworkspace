from Student import Student
'''
author: Nils Golembiewski
'''

def is_integer(str:str):
    try:
        int(str)
        return True
    except:
        return False

def prompt_amount():
    amount = input("What is the amount of students in the group?\n")
    while not amount.isdigit() or not int(amount)>0:
        amount=input("Pleas enter a number larger than 0. Try again.\n")
    return int(amount)

def correct_student_info(student_str:str, initialization:bool):
    try:
        if not is_integer(student_str.split()[0]):
            return False
        if initialization and int(student_str.split()[0]) <0:
            return False
        if int(student_str.split()[0]) >=0:
            _x=student_str.split()[1]
            _y=student_str.split()[2]
        else:
            return True
    except:
        return False
    try:
        _z=student_str.split()[3]
        return False
    except:
        return True

def prompt_student():
    student_str=input("Please enter information about the next student.\n")
    while not correct_student_info(student_str, True):
        student_str=input("Incorrect input, try again.\n")
    return Student(student_str.split()[0],student_str.split()[1],student_str.split()[2])


def prompt_students():
    amount = prompt_amount()
    students=[]
    for _n in range(amount):
        students.append(prompt_student())
    return students

def print_students(students:list):
    print('the group now contains:')
    for student in students:
        print(student)

def search_for_student(students:list,student_str:str):
    index=0
    for student in students:
        if student.student_nr==student_str.split()[0]:
            return index
        index=index+1
    return index

def change_student(students:list, student_str:str):
    index=search_for_student(students, student_str)
    if index == len(students):
        print("student not found, no changes were made")
    else:
        students[index]=Student(student_str.split()[0],student_str.split()[1], student_str.split()[2])
        print('Changes were made:')

def maintain_students(students:list):
    input_nr=0
    while input_nr >=0:
        print_students(students)
        input_str=input('Student number and new name?\n')
        while not correct_student_info(input_str, False):
            input_str=input('Incorrect input, try again.\n')
        input_nr=int(input_str.split()[0])
        if input_nr>=0:
            change_student(students, input_str)


students=prompt_students()
maintain_students(students)
