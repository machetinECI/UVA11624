from sys import stdin
from collections import deque
from sys import setrecursionlimit
setrecursionlimit(100000000)
def main():
    entrada=[int(x) for x in stdin.readline().strip().split()]
    casos=0
    while entrada!=[0,0]:
        if casos!=0:
            print()
        c=entrada[0]
        f=entrada[1]
        mat=[]
        mat.append(["*"]*(c+1))
        orden=[]
        for i in range(f):
            linea=stdin.readline().strip()
            aux=[]
            for j in range(len(linea)-1):
                if linea[j]=="\\" and linea[j+1]=="/":
                    aux.append(0)
                if linea[j]=="/" and linea[j+1]=="\\":
                    aux.append(1)
                if linea[j]=="/" and linea[j+1]=="/":
                    aux.append(2)
                if linea[j]=="\\" and linea[j+1]=="\\":
                    aux.append(3)
            aux=["*"]+aux+["*"]
            mat.append(aux)
        mat.append(["*"]*(c+1))
        par={}
        visitados=set()
        for k in range(1,f+1):
            for p in range(1,c):
                if not (k,p) in par:
                    par[(k,p)]=[(k,p),2]
                    orden.append((k,p))
                    Q=deque()
                    Q.append((k,p))
                    visitados.add((k,p))
                    while Q:
                        u=Q.popleft()
                        if mat[u[0]][u[1]]!="*":
                            i=u[0]
                            j=u[1]
                            #PRIMER CASO
                            if mat[i][j]==0:
                                aux=[]
                                if mat[i-1][j]!=0:
                                    aux.append((i-1,j))
                                if mat[i-1][j-1]==3 or mat[i-1][j-1]==1 or mat[i-1][j-1]=="*":
                                    aux.append((i-1,j-1))
                                if mat[i-1][j+1]==2 or mat[i-1][j+1]==1 or mat[i-1][j+1]=="*":
                                    aux.append((i-1,j+1))
                            #SEGUNDO CASO
                            if mat[i][j]==1:
                                aux=[]
                                if mat[i+1][j]!=1:
                                    aux.append((i+1,j))
                                if mat[i+1][j-1]==2 or mat[i+1][j-1]==0 or mat[i+1][j-1]=="*":
                                    aux.append((i+1,j-1))
                                if mat[i+1][j+1]==3 or mat[i+1][j+1]==0 or mat[i+1][j+1]=="*":
                                    aux.append((i+1,j+1))
                            #TERCER CASO
                            if mat[i][j]==2:
                                aux=[]
                                if mat[i-1][j]!=0 and mat[i-1][j]!=2:
                                    aux.append((i-1,j))
                                if mat[i+1][j]!=1 and  mat[i+1][j]!=2:
                                    aux.append((i+1,j))
                                if mat[i-1][j+1]!=0 and mat[i-1][j+1]!=3 or mat[i-1][j+1]=="*":
                                    aux.append((i-1,j+1))
                                if mat[i+1][j-1]!=1 and mat[i+1][j-1]!=3 or mat[i+1][j-1]=="*":
                                    aux.append((i+1,j-1))
                            #CUARTO CASO :V
                            if mat[i][j]==3:
                                aux=[]
                                if mat[i-1][j]!=0 and mat[i-1][j]!=3:
                                    aux.append((i-1,j))
                                if mat[i+1][j]!=1 and mat[i+1][j]!=3:
                                    aux.append((i+1,j))
                                if mat[i-1][j-1]!=0 and mat[i-1][j-1]!=2 or mat[i-1][j-1]=="*":
                                    aux.append((i-1,j-1))
                                if mat[i+1][j+1]!=1 and mat[i+1][j+1]!=2 or mat[i+1][j+1]=="*":
                                    aux.append((i+1,j+1))
                            #ADYACENTES
                            for v in aux:
                                if mat[v[0]][v[1]]=="*":
                                    if (u[0]==1 or u[0]==f) and (u[1]!=1 and u[1]!=c-1):
                                        par[v]=[u,0]
                                        orden.append(v)
                                    #MACHETE
                                    else:
                                        #PRIMER CASO
                                        if mat[u[0]][u[1]]==0:
                                            if u[1]==c-1:  
                                                if mat[u[0]-1][u[1]]==2 or mat[u[0]-1][u[1]]==0 or mat[u[0]-1][u[1]]=="*":
                                                    par[v]=[u,0]
                                                    orden.append(v)
                                            elif u[1]==1:
                                                if mat[u[0]-1][u[1]]==3 or mat[u[0]-1][u[1]]==0 or mat[u[0]-1][u[1]]=="*":
                                                    par[v]=[u,0]
                                                    orden.append(v)
                                        #SEGUNDO CASO
                                        elif mat[u[0]][u[1]]==1:
                                            if u[1]==c-1:
                                                if mat[u[0]+1][u[1]]==3 or mat[u[0]+1][u[1]]==1 or mat[u[0]+1][u[1]]=="*":
                                                    par[v]=[u,0]
                                                    orden.append(v)
                                            elif u[1]==1:
                                                if mat[u[0]+1][u[1]]==2 or mat[u[0]+1][u[1]]==1 or mat[u[0]+1][u[1]]=="*":
                                                    par[v]=[u,0]
                                                    orden.append(v)
                                        #TERCER CASO
                                        elif mat[u[0]][u[1]]==2:
                                            if u[1]==c-1:
                                                if mat[u[0]-1][u[1]]==0 or mat[u[0]-1][u[1]]==2 or mat[u[0]-1][u[1]]=="*":
                                                    par[v]=[u,0]
                                                    orden.append(v)
                                            elif u[1]==1:
                                                if mat[u[0]+1][u[1]]==1 or mat[u[0]+1][u[1]]==2 or mat[u[0]+1][u[1]]=="*":
                                                    par[v]=[u,0]
                                                    orden.append(v)
                                        #CUARTO CASO
                                        elif mat[u[0]][u[1]]==3:
                                            if u[1]==c-1:
                                                if mat[u[0]+1][u[1]]==1 or mat[u[0]+1][u[1]]==3 or mat[u[0]+1][u[1]]=="*":
                                                    par[v]=[u,0]
                                                    orden.append(v)
                                            elif u[1]==1:
                                                if mat[u[0]-1][u[1]]==0 or mat[u[0]-1][u[1]]==3 or mat[u[0]-1][u[1]]=="*":
                                                    par[v]=[u,0]
                                                    orden.append(v)
                                else:
                                    #BFS
                                    if not v in visitados:
                                        if mat[v[0]][v[1]]==0 or mat[v[0]][v[1]]==1:
                                            if not v in par:
                                                par[v]=[u,2]
                                        else:
                                            if not v in par:
                                                par[v]=[u,2]
                                        orden.append(v)
                                        Q.append(v)
                                        visitados.add(v)
        orden.reverse()
        cont=0
        ciclos={}
        maximo=-1
        visto=set()
        c=False
        #RECONSTRUYE EL CAMINO :V
        while cont<=len(orden)-1:
            aux=0
            actual=orden[cont]
            if par[actual][1]==0:
                c=True
            else:
                if not actual in visto:
                    while par[actual][0]!=actual:
                        aux+=par[actual][1]
                        visto.add(actual)
                        visto.add(par[actual][0])
                        if par[actual][1]==0:
                            c=True
                        actual=par[actual][0]
                    if c:
                        ciclos[actual]=False
                    if not c and aux!=0:
                        if not actual in ciclos:
                            aux+=par[actual][1]
                            ciclos[actual]=aux
                        else:
                            if not ciclos[actual]:
                                ciclos[actual]=False
                            else:
                                ciclos[actual]+=aux
                c=False
            cont+=1
        rta=0
        for x in ciclos:
            if not ciclos[x]:
                pass
            else:
                rta+=1
                if ciclos[x]>maximo:
                    maximo=ciclos[x]
        print("Maze #"+str(casos+1)+":")
        if rta==0:
            print("There are no cycles.")
        else:
            print(str(rta)+" Cycles; the longest has length "+str(maximo)+".")
        entrada=[int(x) for x in stdin.readline().strip().split()]
        casos+=1
    print()
main()
