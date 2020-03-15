# Interviu-Axway

Negru Andrei
Rezolvare test - Software Engineer in Test – intern

Ex1:
    Pentru rezolvarea exercitiului 1 am adaugat in clasa GraphTraversal doua 
    metode pentru parcurgerea in adancime a grafului. Metoda care realizeaza 
    parcurgerea este depthFirstTraversal, care primeste ca parametrii graful si 
    nodul sursa. Aceasta metoda se foloseste de metoda recursiva dfsAux care 
    este apelata de fiecare data cand este intalnit un nod in graf care nu a 
    fost vizitat. Executia se opreste atunci cand toate nodurile la care s-a 
    ajuns cu parcurgerea sunt vizitate.

Ex2:
    Pentru rezolvarea exercitiului 2 au fost adaugate urmatoarele clase
   
    1.Clasa DbManager este folosita pentru a simula o baza de date, gestionand 
    salvarea/incarcarea grafului in/din fisierul my_db.txt. Graful este salvat 
    in fisier respectandu-se formatul: pe fiecare linie primul element este 
    eticheta nodului, urmat de lista etichetelor nodurilor adicente; in cazul in 
    care nodul nu are adiacente, acesta va fi singurul element de pe linia 
    respectiva; in situatia in care graful nu are niciun nod, fisierul este gol.

    Metodele folosite pentru aceste operatiuni sunt saveGraphInDB si 
    loadGraphFromDB.

    2.Clasa Rest este folosita pentru a implementa functionalitatile CRUD pe 
    graf: 
        - get [label] (operatie de Retrieve)
        Functia get apelata fara parametru va afisa toate nodurile din graf cu
        listele de noduri adiacente. Cand este apelata cu parametru, daca nodul 
        respectiv se afla in graf se va afisa lista de noduri adiacente, altfel 
        se va afisa un mesaj care indica acest lucru.
        Metodele care realizeaza aceste operatii sunt get() si get(label).

        - add label1 [label2] (operatie de Create/Update)
        Functia add apelata cu un parametru va adauga un nod nou in graf daca 
        acesta nu exista, atfel se afiseaza un mesaj care sa indice acest lucru.
        Functia add apelata cu doi parametrii diferiti va adauga o muchie in 
        graf daca aceasta nu exista si ambele noduri exista in graf. In caz 
        contrar se vor afisa mesaje care sa indice care dintre parametrii nu 
        sunt valizi.
        Metodele care realizeaza aceste operatii sunt addNode(label1) si 
        addEdge(label1, label2). In cazurile incare se aplica modificarile, se
        va actualiza si fisierul folosit ca baza de date.

        - del [label1] [label2] (operatie de Delete/Update)
        Functia del apelata fara parametrii va sterge tot graful.
        Functia del apelata cu un parametru va sterge nodul respectiv daca 
        exista in graf, atfel va afisa un mesaj care sa indice acest lucru.
        Functia del apelata cu doi parametrii diferiti va sterge o muchie din 
        graf, daca aceasta exista, atfel se vor mesaje care sa indice care 
        dintre parametrii nu sunt valizi.
        Metodele care realizeaza aceste operatii sunt delete(), delNode(label) 
        si delEdge(label1, label2).
    
    3.Clasa Server este folosita pentru a gestiona request-urile primite sub 
    forma de mesaje pe un socket tcp. In clasa Server se face parsarea comenzii 
    si se valideaza din puncte de vedere al formatului (nume functie, numar 
    parametrii etc). In cazul in care comenzile respecta formatul, validarea 
    parametrilor(valori) si rezolvarea request-ului se va efectua de catre clasa
    Rest. Toate cererile primite vor trimite inapoi un mesaj de raspuns 
    corespunzator.

    4.Clasa Client este folosita pentru a testa functionalitatea aplicatiei. 
    Clientul se va trimite cereri catre server si va astepta raspuns. Comenzile 
    disponibile cientului sunt:
        - help
        - quit
        - get [label]
        - add label1 [label2]
        - del [label1] [label2]

Ex3:
    Pentru rezolvarea exercitiului 3 am plecat de la urmatoarele aspecte 
    teoretice privind legatura din graf si arbore:

    Un graf neorientat este arbore daca:
    - nu contine cicluri: intre oricare doua noduri exista o cale unica
    - este conectat: daca se aplica BFS/DFS, toate nodurile ar trebui sa fie 
    accesibile

    Astfel, in clasa Tree care extinde Graph, trebuie sa se asigure ca orice 
    operatie care modifica graful(add/remove node; add/remove edge) nu modifica 
    structura de arbore.

    La adaugarea/stergerea de noduri se poate altera structura de arbore prin 
    faptul ca graful poate sa devina neconectat. Pentru a se evita aceasta 
    problema, metodele add/remove vertex au fost suprascrise si in corpul lor a 
    fost adaugata instructiunea "throw new UnsupportedOperationException();". 
    Drept consecinta, adaugarea/stergerea nodurilor este gestionata automat in 
    celelalte metode de modificare a grafului.

    La adaugarea/stergerea de muchii se pot incalca ambele restrictii pe care 
    graful trebuie sa le indeplineasca pentru a fi arbore. Aceste proprietati 
    sunt verificate cu ajutorul metodelor auxiliare isCyclic(), isTree(), 
    isLeaf().
        1.Adaugarea de muchii
         - intai se verifica daca este posibila inserarea muchiei(cel putin un 
         nod trebuie sa existe in graf)
         - pasul urmator este verificarea conditiilor de arbore
         - daca nu sunt indeplinite conditiile de arbore, modificarile efectuate
         sunt anulate si se va afisa un mesaj corespunzator
         - daca sunt indeplinite conditiile de arbore, graful ramane nemodificat
         si se va afisa un mesaj de succes
        
        2.Stergerea de muchii
         - primul pas este verificarea daca muchia exista in graf
         - pentru a sterge o muchie, si graful sa iti mentina structura de 
         arbore, trebuie ca cel putin unul din capetele muchiei sa fie frunza 
         in arbore. Verificarea se face cu metoda isLeaf(nodul frunza are doar 
         un nod adiacent)
         - daca in muchie sunt gasite noduri frunza atunci vor fi sterse
         - daca nu sunt gasite frunze in muchia dorita, atunci se va afisa un 
         mesaj corespunzator si operatia nu se va efectua

Ex4:
    Pentru a verifica ca functionalitatile implementate se comporta conform 
    asteptatrilor, am implementat in clasa Main mai multe metode care ruleaza 
    diferite teste. Se va evalua output-ul functiilor respective in raport cu 
    rezultatul asteptat.
    Tipurile de teste implementate:
        - testarea componentelor individuale(unit testing)
        - testarea felului in care se combina functionalitatile diferitelor
        componente(integration testing)
    
    Test1: verificarea operatiior pe grafuri din clasa Graph

                        2             4
                        |             |
                        1      6------3------5
                        |      |             |
                        9------7-------------8
    
    Succesiunea de operatii de adaugare noduri/muchii ar trebui sa produca 
    graful exemplificat mai sus. Pentru validare se va compara output-ul 
    functiei de afisare graf din clasa Graph.
    Output: 8[5, 7] 7[9, 6, 8] 6[3, 7] 5[3, 8] 4[3] 3[4, 5, 6] 2[1] 1[2, 9] 9[1, 7]

    Test2: verificarea parcurgerii in adancime(ex2)
    Se va folosi graful de la Test1
    Output: 1 2 9 7 6 3 4 5 8

    Test3: verificarea salvarea/incarcarea in/din fisier a grafului
    Se va folosi graful de mai sus. Se va compara graful salvat in fisier cu cel
    reconstruit pe baza citirii fisierului:
    Graf initial: 8[5, 7] 7[9, 6, 8] 6[3, 7] 5[3, 8] 4[3] 3[4, 5, 6] 2[1] 1[2, 9] 9[1, 7]
    Graf restaurat: 1[2, 9] 2[1] 7[8, 9, 6] 8[5, 7] 9[7, 1] 3[6, 5, 4] 4[3] 5[8, 3] 6[7, 3]

    Test4: verificare construire si mentinere arbore(ex 3)
    Secventa de operatii:
        1.addEdge("1", "2")
        2.addEdge("3", "4")
        3.addEdge("1", "3")
        4.addEdge("3", "4")
        5.removeEdge("1", "4")
        6.removeEdge("1", "3")
        7.removeEdge("3", "4")
    
    In urma operatiilor de mai sus, se asteapta ca arborele sa aiba structura

            3---------1----------2

    Feedback functii de modificare arbore:
        1.Edge inserted successfully
        2.Can not insert the edge. The graph will no longer be a tree
        3.Edge inserted successfully
        4.Edge inserted successfully
        5.No edge between 1 and 4
        6.None of the labels is a leaf
        7.Edge removed successfully


    Arbore final: 3[1] 2[1] 1[2, 3]

    Test5: verificare functionare Rest+DbManager+Client+Server(ex2)

    Testul are ca scop validarea functionarii claselor ca un intreg in cadrul 
    aplicatiei. Initial in baza de date este salvat graful de la testul 1.
    In cadrul testului vor fi evaluate respunsurile date de server la cererile 
    clientului.

    Output:
    Request:get
    Response:7->[8, 9, 6]; 6->[7, 3]; 9->[7, 1]; 8->[5, 7]; 3->[6, 5, 4]; 2->[1]; 5->[8, 3]; 4->[3]; 1->[2, 9];
    
    Request:get 10
    Response:10 not in database
    
    Request:get 1
    Response:[2, 9]
    
    Request:add
    Response:Invalid command
    
    Request:add 1
    Response:1 is already in database
    
    Request:add 10
    Response:10 added successfully in database
    
    Request:add 11
    Response:11 added successfully in database
    
    Request:add 10 10
    Response:
    Labels should be different
    
    Request:add 10 11
    Response:Edge between 10 and 11 added in database
    
    Request:del 12
    Response:12 is not in database
    
    Request:del 11 10
    Response:Edge between 11 and 10 removed from database
    
    Request:get 10
    Response:[]
    
    Request:del 11
    Response:11 deleted successfully from database
    
    Request:del
    Response:Database deleted successfully
    
    Request:get
    Response:The database is empty
    
    Request:quit
    Response:The server is going out!

    Baza de data initiala:
    8 5 7
    7 9 6 8
    6 3 7
    5 3 8
    4 3
    3 4 5 6
    2 1
    1 2 9
    9 1 7

    Baza de date finala:
