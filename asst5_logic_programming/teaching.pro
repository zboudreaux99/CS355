:- include('enrollment.pro').

% Rule for teaches(F, S):
% This rule checks if faculty member F teaches student S in some course section.
teaches(F, S) :-
    section(SectionNumber, _, F),  % F teaches a section with SectionNumber
    enrolledIn(SectionNumber, S). % S is enrolled in that SectionNumber

% Rule for takes(S, C):
% This rule checks if student S is taking some section of course C.
takes(S, C) :-
    section(SectionNumber, C, _),  % SectionNumber belongs to course C
    enrolledIn(SectionNumber, S). % S is enrolled in that SectionNumber

% Rule for teachesTwice(F, S):
% This rule checks if faculty member F teaches student S in 2 or more different course sections.
teachesTwice(F, S) :-
    findall(SectionNumber, 
            (section(SectionNumber, _, F), enrolledIn(SectionNumber, S)), 
            Sections),
    length(Sections, Count),
    Count >= 2. % Ensure F teaches S in at least 2 sections
