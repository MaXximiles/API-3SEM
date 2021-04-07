import "./Modal.css";
import React, { useEffect, useRef, useState } from "react";
import ReactDOM from "react-dom";

const Modal = ({ title, isOpen, setOpen, fadeDuration = 500, children }) => {
  const [wasModalOpen, setWasModalOpen] = useState(isOpen);
  const [fadeTimer, setFadeTimer] = useState(fadeDuration);
  const [modalAnimation, setModalAnimation] = useState("");
  const [modalContentAnimation, setModalContentAnimation] = useState("");
  const ref = useRef();

  useEffect(() => {
    const onBodyClick = (event) => {
      if (ref.current && ref.current.contains(event.target)) {
        return;
      }

      setOpen(false);
    };

    document.body.addEventListener("click", onBodyClick);

    return () => {
      document.body.removeEventListener("click", onBodyClick);
    };
  });

  useEffect(() => {
    if (wasModalOpen !== isOpen) {
      if (!wasModalOpen && isOpen) {
        setModalAnimation("animating fade in");
        setModalContentAnimation("animating scale in");
      } else {
        setModalAnimation("animating fade out");
        setModalContentAnimation("animating scale out");
      }

			const hasBodyOverflown = ({
				clientWidth,
				clientHeight,
				scrollWidth,
				scrollHeight,
			}) => {
				return scrollHeight > clientHeight || scrollWidth > clientWidth;
			};

			document.body.classList.toggle("scrolling", hasBodyOverflown(document.body));

      setFadeTimer(fadeDuration);

      setTimeout(() => {
        setWasModalOpen(isOpen);
      }, fadeDuration / 2);
    } else {
      if (wasModalOpen && isOpen) {
        setModalAnimation("visible active");
        setModalContentAnimation("visible active");
      } else {
        setModalAnimation("hidden");
        setModalContentAnimation("hidden");
      }

      document.body.classList.toggle("dimmed", wasModalOpen && isOpen);

      setFadeTimer(0);
    }
  }, [isOpen, wasModalOpen, fadeDuration]);

  return ReactDOM.createPortal(
    <div
      className={`ui dimmer modals page transition ${modalAnimation}`}
      style={{ animationDuration: fadeTimer }}
    >
      <div
        ref={ref}
        className={`ui standard modal transition ${modalContentAnimation}`}
        style={{ animationDuration: fadeTimer }}
      >
        <div className="header">{title}</div>
        {children}
      </div>
    </div>,
    document.body
  );
};

export default Modal;
