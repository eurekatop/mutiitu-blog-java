import { useLocation } from 'preact-iso';
import { useMousePosition } from '../helpers/useMousePosition';
import { useContext, useState } from 'preact/hooks';
import { AppState } from '..';
import { effect } from '@preact/signals';

export function Header() {
	const { url } = useLocation();
	const {x,y} = useMousePosition();
	const {todos} = useContext(AppState);
	const {environment} = useContext(AppState);
	const {value} = todos
	const [s, setS] = useState("");

	effect(() => {
		//console.debug('Hello', todos.value)
		setS(todos.value.join(','));
	});

	todos.subscribe((value) => {
		//console.debug('Hello', todos.value)
		setS(todos.value.join(','));
	});

	return (
		<header>
			<nav>
				<pre>
					Environment: {JSON.stringify(environment, null, 2)}
				</pre>
				{todos}
				{x} - {y} - {s} - {todos.value} - {value}
				<a href="/" class={url == '/' && 'active'}>
					Home
				</a>
				<a href="/404" class={url == '/404' && 'active'}>
					404
				</a>
			</nav>
		</header>
	);
}
