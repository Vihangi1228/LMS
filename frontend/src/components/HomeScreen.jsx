import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";

function HomeScreen() {
  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-10">
          <div className="card shadow-lg border-0">
            <div className="card-header bg-dark text-white text-center">
              <h2>📚 Library Management System</h2>
            </div>
            <div className="card-body text-center">
              <p className="lead mb-4">
                Welcome to the Library Management System — a complete platform to manage users, authors, books, and reviews efficiently.
              </p>

              <div className="d-grid gap-3 col-8 mx-auto">
                <Link to="/fetchusers" className="btn btn-outline-primary btn-lg">
                  🔍 Fetch User & Library Card
                </Link>
                <Link to="/updateName" className="btn btn-outline-secondary btn-lg">
                  ✏️ Update User Name
                </Link>
                <Link to="/bookDetails" className="btn btn-outline-info btn-lg">
                  📖 View Book & Reviews
                </Link>
                <Link to="/deleteBook" className="btn btn-outline-danger btn-lg">
                  ❌ Delete Book & Reviews
                </Link>
                <Link to="/deleteUser" className="btn btn-outline-warning btn-lg">
                  🗑️ Delete User & Library Card
                </Link>
              </div>
            </div>
            <div className="card-footer text-muted text-center">
              Made with using React & Spring Boot
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default HomeScreen;
