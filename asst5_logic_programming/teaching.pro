:- include('enrollment.pro').

% 1. teaches(F,S) is true if faculty member F teaches student S in some course
teaches(Faculty, Student) :-
    section(Number, _, Faculty),
    enrolledIn(Number, Student).

% 2. takes(S,C) is true if student S is taking some section of course C
takes(Student, Course) :-
    enrolledIn(Number, Student),
    section(Number, Course, _).

% 3. teachesTwice(F,S) is true if faculty F teaches student S in 2+ different course sections
teachesTwice(Faculty, Student) :-
    section(Number1, Course1, Faculty),
    enrolledIn(Number1, Student),
    section(Number2, Course2, Faculty),
    enrolledIn(Number2, Student),
    Number1 \= Number2.