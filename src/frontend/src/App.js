import "./App.scss";
import { TeamPage } from "./pages/TeamPage.js";
import { HashRouter, Route, Routes } from "react-router-dom";
import { MatchPage } from "./pages/MatchPage";
import { HomePage } from "./pages/HomePage";
function App() {
  return (
    <HashRouter>
      <Routes>
        <Route path="/" element={<HomePage />}></Route>
        <Route path="/teams/:teamName" element={<TeamPage />}></Route>
        <Route
          path="/teams/:teamName/matches/:year"
          element={<MatchPage />}
        ></Route>
      </Routes>
    </HashRouter>
  );
}

export default App;
