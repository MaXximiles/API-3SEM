import "./Table.css";
import React, { useEffect, useRef, useState } from "react";
import restAPI from "../apis/restAPI";
import ContextMenu from "./ContextMenu";

const Table = ({ data, onEdit, onDelete }) => {
  const [columnStyle, setColumnStyle] = useState([]);
  const [contextPosition, setContextPosition] = useState({});
  const [selectedItem, setSelectedItem] = useState({});
  const thead = useRef();
  const tbody = useRef();

  useEffect(() => {
    const onBodyClick = () => {
      setContextPosition({ left: null, top: null });
      setSelectedItem({});
    };

    const onBodyContextMenu = (event) => {
      if (tbody.current && !tbody.current.contains(event.target)) {
        setContextPosition({ left: null, top: null });
        setSelectedItem({});
      }
    };

    document.body.addEventListener("click", onBodyClick);
    document.body.addEventListener("contextmenu", onBodyContextMenu);

    return () => {
      document.body.removeEventListener("click", onBodyClick);
      document.body.removeEventListener("contextmenu", onBodyContextMenu);
    };
  }, []);

  useEffect(() => {
    if (tbody.current.children[0]) {
      const firstRow = tbody.current.children[0];

      var bodyCellsSize = [];
      for (const child of firstRow.children) {
        bodyCellsSize = [...bodyCellsSize, child.clientWidth];
      }

      var headerCellsSize = [];
      for (const child of thead.current.children) {
        headerCellsSize = [...headerCellsSize, child.clientWidth];
      }

      const columnsSize = headerCellsSize.map((cellSize, index) => {
        if (index === 0 || index === headerCellsSize.length - 1) {
          if (index === 0) {
            return bodyCellsSize[index] > cellSize
              ? { minWidth: `${bodyCellsSize[index]}px` }
              : { minWidth: `${cellSize}px` };
          } else {
            return { width: "100%" };
          }
        } else {
          return bodyCellsSize[index] > cellSize
            ? { minWidth: `${bodyCellsSize[index] + 1}px` }
            : { minWidth: `${cellSize + 1}px` };
        }
      });

      setColumnStyle(columnsSize);
    }
  }, [data]);

  const editItem = (event, item) => {
    event.stopPropagation();

    onEdit(item);
  };

  const deleteItem = (event, item) => {
    event.stopPropagation();

    onDelete(item);
  };

  const uploadFile = (e, selectedItem) => {
    const input = document.createElement("input");
    input.id = "upload";
    input.type = "file";
    input.style.display = "none";

    input.onchange = async (e) => {
      const file = e.target.files[0];

      const formData = new FormData();
      formData.append("arquivo", file);

      await restAPI.post(
        `/arquivos/upload/${selectedItem.codelistid}`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
    };

    input.click();
  };

  const createLEP = async (e, selectedItem) => {
    await restAPI.get(`/lep/gerarlep?codelistid=${selectedItem.codelistid}`);
  };

  const onContextMenu = (event, item) => {
    event.preventDefault();

    setSelectedItem(item);
    setContextPosition({ left: event.clientX, top: event.clientY });
  };

  const renderContextMenu = () => {
    if (contextPosition.left || contextPosition.top) {
      return (
        <ContextMenu xy={contextPosition}>
          <div className="ui secondary vertical menu">
            <div
              className="ui dropdown item"
              onClick={(e) => editItem(e, selectedItem)}
            >
              Editar Bloco
            </div>
            <div
              className="ui dropdown item"
              onClick={(e) => deleteItem(e, selectedItem)}
            >
              Deletar Bloco
            </div>
            <div
              className="ui dropdown item"
              onClick={(e) => uploadFile(e, selectedItem)}
            >
              Fazer upload
            </div>
            <div
              className="ui dropdown item"
              onClick={(e) => createLEP(e, selectedItem)}
            >
              Criar LEP
            </div>
          </div>
        </ContextMenu>
      );
    }

    return null;
  };

  const rendredTableHead = data.length
    ? Object.keys(data[0]).map((key, index) => {
        return (
          <th key={key} style={columnStyle[index]}>
            {key}
          </th>
        );
      })
    : null;

  const renderedTableBody = data.map((item, rowIndex) => {
    const renderedTableCells = Object.entries(item).map((value, index) => {
      if (Array.isArray(value[1])) {
        return (
          <td key={value[0]} data-label={value[0]} style={columnStyle[index]}>
            {value[1]
              .map((traco) => {
                return traco.tracodocnome;
              })
              .join(", ")}
          </td>
        );
      }

      return (
        <td key={value[0]} data-label={value[0]} style={columnStyle[index]}>
          {value[1]}
        </td>
      );
    });

    return (
      <tr
        key={rowIndex}
        className={selectedItem === item ? "active" : ""}
        onContextMenu={(e) => onContextMenu(e, item)}
      >
        {renderedTableCells}
      </tr>
    );
  });

  return (
    <div className="Table">
      {renderContextMenu()}
      <table className="ui selectable celled table unstackable">
        <thead>
          <tr ref={thead}>{rendredTableHead}</tr>
        </thead>
        <tbody ref={tbody}>{renderedTableBody}</tbody>
        <tfoot className="full-width">
          <tr>
            <th colSpan={data.length ? Object.keys(data[0]).length : 0}>
              <div
                className="ui right floated small primary labeled icon button"
                onClick={(e) => editItem(e)}
              >
                <i className="plus icon"></i>
                Adicionar Bloco
              </div>
            </th>
          </tr>
        </tfoot>
      </table>
    </div>
  );
};

export default Table;
