import { useEffect, useRef, useState } from "react";

export const useMousePosition2 = () => {
  const [position, setPosition] = useState({ x: 0, y: 0 });

  
  useEffect(() => {
    console.debug("useMousePosition called");
    const setFromEvent = (e) => {
        setPosition({ x: e.clientX, y: e.clientY });
    }

    window.addEventListener("mousemove", setFromEvent);

    return () => {
      window.removeEventListener("mousemove", setFromEvent);
    };
  }, []);

  return position;
};


const setStates = []


export const useMousePosition= () => {
  const [position, setPosition] = useState({ x: 0, y: 0 });
  
  
  useEffect(() => {
    //console.debug("useMousePosition called");

    const setFromEvent = (e) => {
      //console.log ( "set position called", e.clientX, e.clientY );


      const s = { x: e.clientX, y: e.clientY }

      window.requestAnimationFrame( () => {
        for (let setState of setStates) {
          setState(s);
      }
    });

    }
    
    if ( setStates.length === 0 ) {
      window.addEventListener("mousemove", setFromEvent);
    }

    setStates.push( setPosition ); 

    //return () => {
    //  window.removeEventListener("mousemove", setFromEvent);
    //};
  }, []);

  return position;
};
