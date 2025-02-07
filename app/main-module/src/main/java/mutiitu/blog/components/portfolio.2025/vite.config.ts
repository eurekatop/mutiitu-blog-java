import { defineConfig } from 'vite';
import preact from '@preact/preset-vite';
//import sass from 'vite-plugin-sass';

// https://vitejs.dev/config/
export default defineConfig({
	plugins: [
		preact({
			prerender: {
				enabled: true,
				renderTarget: '#app',
				additionalPrerenderRoutes: ['/404'],
				previewMiddlewareEnabled: true,
				previewMiddlewareFallback: '/404',
			},
		}),
		//sass({
		//	//injectGlobalPaths: ['./src/style/global.scss'],
		//}),
	],
	//optimizeDeps:{
	//	exclude: ['jjpixi.js'],
	//},
	//ssr:{
	//	noExternal:[
	//		'jjpixi.js'
	//	],		
	//},
	// build:{
	// 	rollupOptions: {
	// 		output: {
	// 		  manualChunks(id) {
	// 			if (id.includes('node_modules')) {
	// 			  return id.toString().split('node_modules/')[1].split('/')[0].toString();
	// 			}
	// 		  }
	// 		}
	// 	  },
	// 	minify: 'esbuild',
    //     chunkSizeWarningLimit: 5000,
    //     modulePreload: true,
    //     sourcemap: 'hidden',
    //     outDir: 'dist',
    //     assetsDir: 'assets',
	// 	emitAssets: true,
	// 	ssrEmitAssets: true,
	// }
});
