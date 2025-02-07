import { LocationProvider, Router, Route, hydrate, prerender as ssr, lazy } from 'preact-iso';

import { Header } from './components/Header.jsx';
import { Home } from './pages/Home/index.jsx';


import './style.scss';
import { createContext, options } from 'preact';
import { computed, signal } from '@preact/signals';


function applyColors() {
	// Modify Css var
	const root = document.documentElement;
    //root.style.setProperty('--secondary-theme-color-dark', 'black');
}


options.debounceRendering = (cb) => {
	requestAnimationFrame ( () => {
			requestIdleCallback(cb)
		})
}




function createAppState() {
	const todos = signal([1,2,3,4]);
	const mousePosition = signal();
	const environment = {
		ssr: false,
		url: ''
	}

	const completed = computed(() => {
	  return todos.value.filter(todo => todo > 2).length
	});
  
	return { todos, completed, mousePosition, environment}
}

// window states
//window.addEventListener("mousemove", (e) => {
//	window.appState.mousePosition.set({ x: e.clientX, y: e.clientY });
//});

//setInterval( () => {
//	console.debug ( "Interval") ;
//	
//    // Increment the todos array every second
//	const _newState = state.todos.value.map((todo) => todo);
//	_newState.push(_newState.length + 1);
//	state.todos.value = _newState;
//
//	//state.todos.value.push(state.todos.value.length + 1);
//}, 1000 );


const state = createAppState();
export const AppState = createContext(state);





export function App() {
	// Lazy load 
	const NotFound = lazy(() => import('./pages/_404.jsx'));
	const Resume = lazy(() => import('./pages/Resume/index.js'));

	return (
			<LocationProvider>
				<Header />
				<main>
					<Router>
						<Route path="/" component={Home} />
						<Route path="/resume" component={Resume} />
						<Route default component={NotFound} />
						<NotFound default />
					</Router>
					</main>
			</LocationProvider>
	);
}

if (typeof window !== 'undefined') {
	applyColors();

	hydrate(
		<AppState.Provider value={state}>
			<App />
		</AppState.Provider>
		, 
	document.getElementById('app'));
}

export async function prerender(data) {
	//console.log("d----------------------- data");
	//console.log(data);
	//return await ssr(<App {...data} />);

	const state = createAppState();
	state.environment = data;

	const res = await fetch("https://blog.mutiitu.com/blog/get/1?content-type=json");
	console.log(res)

	return await ssr(
	<AppState.Provider value={state}>
		<App />
	</AppState.Provider> );
}

