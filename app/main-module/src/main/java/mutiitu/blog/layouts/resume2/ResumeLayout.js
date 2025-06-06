System.import('/mutiitu/components/card/scripts/card.js');
System.import('/mutiitu/components/markdown/scripts/markdown.js')  

System.import('/mutiitu/components/gameoflife/scripts/gameoflife.js').then ( (module) => {
    console.log ( module )
    window.module = module;
})
.catch ( (error) => {
    console.log ( error )
});