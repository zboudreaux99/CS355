:- include('enrollment.pro').

% Rule for teaches(F, S):
% This rule checks if faculty member F teaches student S in some course section.
teaches(F, S) :-
    teaches_section(F, Section),  % F teaches Section
    enrolled(S, Section).        % S is enrolled in Section

% Rule for takes(S, C):
% This rule checks if student S is taking some section of course C.
takes(S, C) :-
    enrolled(S, Section),        % S is enrolled in Section
    course_section(C, Section).  % Section belongs to course C

% Rule for teachesTwice(F, S):
% This rule checks if faculty member F teaches student S in 2 or more different course sections.
teachesTwice(F, S) :-
    teaches(F, S),               % F teaches S in at least one section
    findall(Section, (teaches_section(F, Section), enrolled(S, Section)), Sections),
    length(Sections, Count),
    Count >= 2.                  % Ensure F teaches S in at least 2 sections

