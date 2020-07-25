1. 1. **Para acceder a psql en WSL**
    ```
    sudo -u postgres psql
    ```
    
2. **Crear Base de datos**
    ```SQL
    create role calceUser with createdb login password 'calceteam.1234';
    ALTER ROLE calceUser SUPERUSER;
    create database calceDb;
    alter database calceDb owner to calceUser;
    grant all privileges on database calceDb to calceUser;
    ```