:- include('enrollment.pro').

teaches(Faculty,Student) :-
    section(Section, _, Faculty),
    enrolledIn(Section, Student).

takes(Student,Course) :-
    section(Section, Course, _),
    enrolledIn(Section, Student).

teachesTwice(Faculty, Student) :-
    section(Section1, _, Faculty),
    section(Section2, _, Faculty),
    Section1 \= Section2,
    enrolledIn(Section1, Student),
    enrolledIn(Section2, Student).