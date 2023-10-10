- [ ] I want that a component cant render another components
- [ ] I want that a component cant inject another components
- [ ] I want a expressive way of define components in HTML by example
```
<html>
<head>
</head>
<body>
    ostras
    {{ subtitle }}
    // server side rendered
    <MyUiComponent  ssr arg1="{i}" arg2="hola"></MyUiComponent>

    //client side rendered
    <MyUiComponent csr arg1="{i}" arg2="hola"></MyUiComponent>
</body>
</html>
```
- [ ] I want in my markup syntax that a component can be served rendered ssr or delayed to client csr
- [ ] I want my markup syntax that can parse loop and conditional expressions. By example:
```
    for i=0; to subtitle; do;
        <MyUiComponent ssr arg1="{i}" arg2="hola"></MyUiComponent>
    done;
```
- [ ] I want that i can use more that one storage technology ( mysql, flat file, etc... )
- [ ] I want that i can serve components in front via with ajax ( by example 'htmx' )
- [ ] I want to show a simple Html page with a body that is a list of components a header, a footer and a menu.
- [ ] I want to work on vscode with hot reload and easy debugging.
- [ ] I want that an user can define models in a easy way.
- [ ] The persistence model can be queried from more than one application.
- [ ] Frontend editor admin / page.
- [ ] 