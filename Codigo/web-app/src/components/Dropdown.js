import React, { useEffect, useRef, useState, useLayoutEffect } from "react";

const Dropdown = ({ label, options, selected, onSelectedChange }) => {
  const [isOpen, setOpen] = useState(false);
  const [search, setSearch] = useState("");
  const [selectedSelections, setSelectedSelections] = useState([]);
  const [visible, setVisible] = useState(false);
  const [inputWidth, setInputWidth] = useState("auto");
  const inputRef = useRef();
  const measurerRef = useRef();
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
  }, []);

  useEffect(() => {
    setVisible(true);
  }, [search]);

  useLayoutEffect(() => {
    if (visible) {
      const rect = measurerRef.current.getBoundingClientRect();
      setInputWidth(rect.width);
      setVisible(false);
    }
  }, [visible]);

  const openMenu = () => {
    if (inputRef.current) {
      inputRef.current.focus();
    }

    setOpen(!isOpen);
  };

  const renderMenu = () => {
    const renderedOptions = options.map((option) => {
      if (
        selected.includes(option) ||
        (!option.label.includes(search) && search)
      ) {
        return null;
      }

      return (
        <div
          key={option.value}
          className="item"
          onClick={() => onSelectedChange((selected) => [...selected, option])}
        >
          {option.label}
        </div>
      );
    });

    const checkOptions = renderedOptions.some((option) => {
      return option != null;
    });

    if (checkOptions) {
      return renderedOptions;
    }

    return <div className="message">Nenhum resultado encontrado.</div>;
  };

  const toggleSelection = (event, option) => {
    event.stopPropagation();

    if (event.shiftKey) {
			var initialIndex = selected.indexOf(selectedSelections[selectedSelections.length - 1]);
      var endIndex = selected.indexOf(option);
			
			if (initialIndex > endIndex) {
				const temp = initialIndex;
				initialIndex = endIndex;
				endIndex = temp;
			}

			setSelectedSelections((selectedSelections) => [
				...selectedSelections,
				...selected.filter((selectedOption, index) => (index >= initialIndex && index <= endIndex)),
			]);
    } else if (event.ctrlKey) {
      if (selectedSelections.includes(option)) {
				console.log(selectedSelections);
        setSelectedSelections(
          selectedSelections.filter(
            (selected) => selected.value !== option.value
          )
        );
      } else {
        setSelectedSelections((selectedSelections) => [
          ...selectedSelections,
          option,
        ]);
      }
    } else {
				setSelectedSelections([option]);
    }
  };

  const unselectSelection = (event, option) => {
    event.stopPropagation();

    onSelectedChange(
      selected.filter(
        (selectedOption) =>
          !selectedSelections.includes(selectedOption) &&
          selectedOption !== option
      )
    );

    setSelectedSelections([]);
  };

  const renderedSelected = selected.map((option) => {
    return (
      <div
        key={option.value}
        className={`ui label transition visible ${
          selectedSelections.includes(option) ? "active" : null
        }`}
        style={{ display: "inline-block !important", cursor: "pointer" }}
        onClick={(e) => toggleSelection(e, option)}
      >
        {option.label}
        <i
          className="delete icon"
          onClick={(e) => unselectSelection(e, option)}
        ></i>
      </div>
    );
  });

  return (
    <div ref={ref} className="field">
      <label className="label">{label}</label>
      <div
        className={`ui fluid search multiple selection dropdown ${
          isOpen ? "visible active" : ""
        }`}
        onClick={openMenu}
      >
        <i className="dropdown icon" />
        {renderedSelected}
        <input
          ref={inputRef}
          className="search"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          style={{ width: inputWidth + 1 }}
        ></input>
        <span ref={measurerRef} style={{ display: "inline" }} className="sizer">
          {visible && search}
        </span>
        <div className="text">{selected.label}</div>
        <div className={`menu ${isOpen ? "visible transition" : ""}`}>
          {renderMenu()}
        </div>
      </div>
    </div>
  );
};

export default Dropdown;
