let mu =  {
  _self : {
    router: undefined,
    htmx:window.htmx,
    dispatch:undefined,
    action:undefined
  },

  init: () => {
    //const centerElement = document.getElementById("center");

    //public
    _self.htmx = window.htmx
    _self.router = new Navigo('/');
    _self.dispatch = dispatch
    _self.action = action

    routes (_self)

    return _self;
  },
}


const _self = mu._self

/**
 * Dispatch and htmx request
 * @param {*} method 
 * @param {*} url 
 * @param {*} htmx options 
 * @returns 
*/
function dispatch(method, url, options) {
  return _self.htmx.ajax(method, url, options);
}

/**
 * Promisify and htmx-ajax request 
 * @param {*} method 
 * @param {*} url 
 * @returns 
 */
function action(method, url ){
  const resultPromise = new Promise(async (resolve, reject) => {
    await _self.dispatch(method, url, {
      swap: 'none',
      handler: (elt, info) => {
          if (info.xhr.status === 200) {
              resolve(info.xhr.responseText);
          } else {
              reject({
                status:info.xhr.status,
                response:info.xhr.responseText
              });
          }
      }
    });
  });
  
  return resultPromise;
}


/**
 * Routes
 * TODO: /page  and /actions not are allowed(backend)
*/
function routes () {
      _self.router.on('/admin/about', function () {
        console.log ("do something")
      });
      _self.router.on('/admin', function () {});

      _self.router.on('/admin/configuration', function (payload) {
        dispatch('GET', `/admin/page/config`, {target:'#center', swap:'innerHTML'})
      });

      // blogPost
      _self.router.on('/admin/blog', function (payload) {
        dispatch('GET', `/admin/page/blog-form`, {target:'#center', swap:'innerHTML'})
      });
      _self.router.on('/admin/blog/:id', function (payload) {
        dispatch('GET', `/admin/page/blog-form?id=${payload.data.id}`, {target:'#center', swap:'innerHTML'})
      });
      _self.router.on('/admin/blog-list', function (payload) {
        dispatch('GET', `/admin/page/blog-list`, {target:'#center', swap:'innerHTML'})
      });

      // author
      _self.router.on('/admin/author', function (payload) {
        dispatch('GET', `/admin/page/author-form`, {target:'#center', swap:'innerHTML'})
      });
      

      // cmsEntry
      _self.router.on('/admin/cms-entry', function (payload) {
        dispatch('GET', `/admin/page/new-entry`, {target:'#center', swap:'innerHTML'})
      });
      _self.router.on('/admin/cms-entry/:id', function (payload) {
        dispatch('GET', `/admin/page/new-entry?id=${payload.data.id}`, {target:'#center', swap:'innerHTML'})
      });
      
      _self.router.resolve();
}




window.addEventListener("DOMContentLoaded", () => {
  window.mu = mu.init();
})

