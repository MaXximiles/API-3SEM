import "./Table.css";
import React, { useEffect, useRef, useState } from "react";
import Modal from "./Modal";
import ContextMenu from "./ContextMenu";
import CodelistGenerate from "./CodelistGenerate";

import restAPI from "../apis/restAPI";

const Table = ({ data, onEdit, onDelete, docId, filter, reload }) => {
  const [columnStyle, setColumnStyle] = useState([]);
  const [contextPosition, setContextPosition] = useState({});
  const [selectedItem, setSelectedItem] = useState({});
  const [isGenerating, setIsGenerating] = useState(false);
  const [generationType, setGenerationType] = useState(null);
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

      alert("Arquivo salvo com sucesso!");
      reload();
    };

    input.click();
  };

  const createLEP = async (e, selectedItem) => {
    await restAPI.get(`/lep/gerarlep?codelistid=${selectedItem.codelistid}`);

    alert("LEP gerada com sucesso!");
    reload();
  };

  const generate = async (generate, revision) => {
    if (generate === "full") {
      await restAPI.get(
        `/codelist/gerarfull?docid=${docId}&tracoid=${filter[0].value}&revisao=${revision}`
      );
    } else {
      await restAPI.get(
        `/codelist/gerardelta?docid=${docId}&tracoid=${filter[0].value}&revisao=${revision}`
      );
    }

    alert(`${generate} gerado com sucesso!`);
    setIsGenerating(false);
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

  const renderModal = () => {
    return (
      <Modal
        title={`Selecione uma revisão para gerar o ${generationType} do manual`}
        isOpen={isGenerating}
        setIsOpen={setIsGenerating}
      >
        <CodelistGenerate
          docId={docId}
          tracoId={filter.length > 0 ? filter[0].value : null}
          generate={generationType}
          onSubmit={generate}
        />
      </Modal>
    );
  };

  var indexSub = 0;
  const rendredTableHead = data.length
    ? Object.keys(data[0]).map((key, index) => {
        if (key === "codelistcaminho" || key === "documentoid") {
          indexSub++;
          return;
        }

        const colNames = () => {
          switch (key) {
            case "codelistid":
              return "ID";
            case "codelistsecao":
              return "Seção";
            case "codelistsubsecao":
              return "Subseção";
            case "codelistnomebloco":
              return "Bloco";
            case "codelistnumbloco":
              return "Número";
            case "codelistcodebloco":
              return "Código";
            case "tracos":
              return "Traços";
            case "arquivos":
              return "Arquivos";
            case "tags":
              return "Tags";
            default:
              return key;
          }
        };

        return (
          <th key={key} style={columnStyle[index - indexSub]}>
            {colNames()}
          </th>
        );
      })
    : null;

  const renderedTableBody = data.map((item, rowIndex) => {
    indexSub = 0;
    const renderedTableCells = Object.entries(item).map((value, index) => {
      if (value[0] === "codelistcaminho" || value[0] === "documentoid") {
        indexSub++;
        return;
      }

      if (Array.isArray(value[1])) {
        return (
          <td
            key={value[0]}
            data-label={value[0]}
            style={columnStyle[index - indexSub]}
          >
            {value[1]
              .map((array) => {
                return Object.values(array)[1];
              })
              .join(", ")}
          </td>
        );
      }

      return (
        <td
          key={value[0]}
          data-label={value[0]}
          style={columnStyle[index - indexSub]}
        >
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
      {renderModal()}
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
              <div
                className={`ui right floated small primary labeled icon button ${
                  filter.length !== 1 ? "disabled" : ""
                }`}
                onClick={() => {
                  setGenerationType("full");
                  setIsGenerating(true);
                }}
              >
                <i className="book icon"></i>
                Gerar Full
              </div>
              <div
                className={`ui right floated small primary labeled icon button ${
                  filter.length !== 1 ? "disabled" : ""
                }`}
                onClick={() => {
                  setGenerationType("delta");
                  setIsGenerating(true);
                }}
              >
                <i className="book icon"></i>
                Gerar Delta
              </div>
            </th>
          </tr>
        </tfoot>
      </table>
    </div>
  );
};

export default Table;
